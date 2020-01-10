package st.southsea.blog.module.back;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import st.southsea.blog.base.Result;
import st.southsea.blog.base.module.BaseModule;
import st.southsea.blog.bean.Comment;
import st.southsea.blog.bean.User;
import st.southsea.blog.service.ArticleService;
import st.southsea.blog.service.CommentService;
import st.southsea.blog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: South
 * @Date: 2019-05-13 08:40
 */
@IocBean
@At("/auth/comment")
@Filters(@By(type = CheckSession.class, args = {"user", "/auth/login"}))
public class BCommentModule extends BaseModule {

    @Inject
    private CommentService commentService;

    @Inject
    private UserService userService;

    @Inject
    private ArticleService articleService;

    // 主页
    @At("/")
    @Ok("beetl:/auth/comment/index.html")
    public void index(HttpSession session) {
    }

    // 表格
    @At("/list")
    @POST
    public Object list(Comment comment, int page, int limit, HttpSession session) {
        Pager pager = new Pager(page, limit);
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断管理员
        if (user.getPermissionId() != 1) {
            comment.setUser(user);
        }
        List<Comment> commentList = commentService.query(comment, pager);
        return layuiTable(commentList, commentService.count(comment));
    }

    // 编辑页面
    @At("/edit/?")
    @Ok("beetl:/auth/comment/edit.html")
    public Object edit(long commentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("comment", commentService.fetch(commentId));
        return map;
    }

    // 编辑评论
    @At
    @POST
    public Result edit(Comment comment, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断评论文章是否存在
        if (Lang.isNotEmpty(articleService.fetch(comment.getArticleId()))) {
            // 判断是否为管理员
            if (user.getPermissionId() == 1) {
                commentService.update(comment);
                return ajaxSuccess("修改成功");
            } else {
                // 判断用户修改的是否为自己的评论
                if (user.getEmail().equals(commentService.fetch(comment.getCommentId()).getEmail())) {
                    commentService.update(comment);
                    return ajaxSuccess("修改成功");
                }
            }
        }
        return ajaxError("修改失败");
    }

    // 单个删除
    @At
    @POST
    public Result delete(long commentId, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断是否为管理员
        if (user.getPermissionId() == 1) {
            commentService.delete(commentId);
            return ajaxSuccess("删除成功");
        } else {
            // 判断用户修改的是否为自己的评论
            if (user.getEmail().equals(commentService.fetch(commentId).getEmail())) {
                commentService.delete(commentId);
                return ajaxSuccess("删除成功");
            }
        }
        return ajaxError("删除失败");
    }

    // 批量删除
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result batchDel(List<Comment> commentList, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断管理员
        if (user.getPermissionId() == 1) {
            commentService.delete(commentList);
            ajaxSuccess("删除成功");
        } else {
            // 判断用户自身评论总集中是否包含打算产出的评论集
            if (user.getCommentList().containsAll(commentList)) {
                commentService.delete(commentList);
                ajaxSuccess("删除成功");
            }
        }
        return ajaxError("删除失败");
    }
}
