package st.southsea.blog.module.front;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.filter.CheckSession;
import st.southsea.blog.base.Result;
import st.southsea.blog.base.module.BaseModule;
import st.southsea.blog.bean.Comment;
import st.southsea.blog.bean.User;
import st.southsea.blog.service.ArticleService;
import st.southsea.blog.service.CommentService;
import st.southsea.blog.service.UserService;

import javax.servlet.http.HttpSession;

/**
 * @Author: South
 * @Date: 2019-05-13 08:40
 */
@IocBean
@At("/comment")
@Filters()
public class CommentFModule extends BaseModule {

    @Inject
    private CommentService commentService;

    @Inject
    private UserService userService;

    @Inject
    private ArticleService articleService;

    // 发表评论
    @At
    @POST
    @Filters(@By(type = CheckSession.class, args = {"user", "/auth/login"}))
    public Result add(Comment comment, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断评论文章是否存在
        if (Lang.isNotEmpty(articleService.fetch(comment.getArticleId()))) {
            // 判断用户发表的是否为自己的评论
            if (user.getEmail().equals(comment.getEmail())) {
                commentService.insert(comment);
                return ajaxSuccess("评论成功");
            }
        }
        return ajaxError("评论失败");
    }

}
