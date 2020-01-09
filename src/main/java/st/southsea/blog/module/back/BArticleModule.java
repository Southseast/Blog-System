package st.southsea.blog.module.back;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import st.southsea.blog.base.Result;
import st.southsea.blog.base.module.BaseModule;
import st.southsea.blog.bean.Article;
import st.southsea.blog.bean.User;
import st.southsea.blog.service.ArticleService;
import st.southsea.blog.service.LabelService;
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
@At("/auth/article")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class BArticleModule extends BaseModule {

    @Inject
    private ArticleService articleService;

    @Inject
    private UserService userService;

    @Inject
    private LabelService labelService;

    // 主页
    @At("/")
    @Ok("beetl:/auth/article/index.html")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("labelList", labelService.query());
        return map;
    }

    // 表格
    @At("/list")
    @POST
    public Object list(Article article, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<Article> articleList = articleService.query(article, pager);
        return layuiTable(articleList, articleService.count());
    }

    // 编辑页面
    @At("/edit/?")
    @Ok("beetl:/auth/article/edit.html")
    public Object edit(long articleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("labelList", labelService.query());
        map.put("article", articleService.fetchLinks(articleId));
        return map;
    }

    // 编辑文章
    @At
    @POST
    public Result edit(Article article) {
        if (articleService.fetch(article.getArticleId()) != null) {
            articleService.update(article);
            ajaxSuccess("修改成功");
        }
        return ajaxSuccess("修改失败");
    }

    // 编辑页面
    @At("/add")
    @Ok("beetl:/auth/article/add.html")
    public Object add() {
        Map<String, Object> map = new HashMap<>();
        map.put("labelList", labelService.query());
        return map;
    }

    // 编辑文章
    @At
    @POST
    public Result add(Article article) {
        if (articleService.fetch(article.getArticleId()) == null) {
            articleService.insert(article);
            ajaxSuccess("发布成功");
        }
        return ajaxSuccess("发布失败");
    }

    // 单个删除
    @At
    @POST
    public Result delete(long articleId, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断是否为管理员
        if (user.getPermissionId() == 1) {
            articleService.delete(articleId);
            return ajaxSuccess("删除成功");
        }
        return ajaxSuccess("删除失败");
    }

    // 批量删除
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result delete(List<Article> articleList, HttpSession session) {
        User user = userService.fetch((String) session.getAttribute("user"));
        // 判断是否为管理员
        if (user.getPermissionId() == 1) {
            articleService.delete(articleList);
            return ajaxSuccess("删除成功");
        }
        return ajaxSuccess("删除失败");
    }

}
