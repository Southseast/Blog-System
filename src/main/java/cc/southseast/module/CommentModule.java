package cc.southseast.module;

import cc.southseast.base.Result;
import cc.southseast.base.module.BaseModule;
import cc.southseast.bean.Comment;
import cc.southseast.bean.User;
import cc.southseast.service.CommentService;
import cc.southseast.service.ConfigService;
import cc.southseast.service.UserService;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cc.southseast.module.UploadModule.uploads;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 08:40
 * @Version 1.0
 */
@IocBean
@At("/auth/comment")
@Filters(@By(type = CheckSession.class, args = {"user", "/auth/login"}))
public class CommentModule extends BaseModule {

    @Inject
    private CommentService commentService;

    @Inject
    private UserService userService;

    @Inject
    private ConfigService configService;

    // 评论一览
    @At("/")
    @Ok("jsp:template/auth/comment/index")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) session.getAttribute("user")));
        map.put("config", configService.fetch(1));
        return map;
    }

    // 查询所有评论
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

    // 写评论
    @At("/edit")
    @Ok("jsp:template/auth/comment/edit")
    public void edit() {
    }

    // 更新评论
    @At("/edit/?")
    @Ok("jsp:template/auth/comment/edit")
    public Object edit(long commentId) {
        return commentService.fetch(commentId);
    }

    // 发表/更新评论
    @At
    @POST
    public Result edit(Comment comment, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断是否管理员
        if (user.getPermissionId() == 1) {
            if (comment.getArticleId() == 0) {
                return ajaxError("评论失败！");
            } else if (comment.getCommentId() == 0) {
                commentService.insert(comment);
            } else {
                commentService.update(comment);
            }
        } else {
            // 判断普通用户发表/修改的是否为自己的ID
            if (user.getEmail().equals(comment.getEmail())) {
                if (comment.getArticleId() == 0) {
                    return ajaxError("评论失败！");
                } else if (comment.getCommentId() == 0) {
                    commentService.insert(comment);
                } else {
                    commentService.update(comment);
                }
            } else {
                return ajaxError("评论失败！");
            }
        }

        return ajaxSuccess("评论成功！");
    }

    // 删除评论
    @At
    @POST
    public Result delete(long commentId, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));

        if (user.getPermissionId() == 1) {
            commentService.delete(commentId);
        } else {
            if (user.getEmail().equals(commentService.fetch(commentId).getEmail())) {
                commentService.delete(commentId);
            } else {
                return ajaxError("删除失败！");
            }
        }
        return ajaxSuccess("删除成功！");
    }

    // 图片接口
    @At("/upload")
    @POST
    @Ok("raw:json")
    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:upload"})
    public Object upload(HttpSession session, @Param("file") TempFile tempFile) throws IOException {
        String path = "../webapps/Blog_System/resources/comment";
        uploads(path, tempFile);
        return uploads(path, tempFile);
    }

    // 删除评论
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result deleteList(List<Comment> commentList, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        int count = 0;
        for (Comment c : commentList) {
            for (Comment oc : user.getCommentList()) {
                if (oc.getCommentId() == c.getCommentId()) {
                    count++;
                }
            }
        }

        // 判断管理员
        if (user.getPermissionId() == 1) {
            commentService.delete(commentList);
        } else if (count == commentList.size()) {
            commentService.delete(commentList);
        } else {
            ajaxError("删除失败！");
        }
        return ajaxSuccess("删除成功");
    }

}
