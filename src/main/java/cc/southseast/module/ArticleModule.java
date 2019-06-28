package cc.southseast.module;

import cc.southseast.base.Result;
import cc.southseast.base.module.BaseModule;
import cc.southseast.bean.Article;
import cc.southseast.service.ArticleService;
import cc.southseast.service.ConfigService;
import cc.southseast.service.LabelService;
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
@At("/auth/article")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class ArticleModule extends BaseModule {

    @Inject
    private ArticleService articleService;

    @Inject
    private UserService userService;

    @Inject
    private LabelService labelService;

    @Inject
    private ConfigService configService;

    // 文章一览
    @At("/")
    @Ok("jsp:template/auth/article/index")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) session.getAttribute("user")));
        map.put("config", configService.fetch(1));
        map.put("labelList", labelService.query());
        return map;
    }

    // 查询所有文章
    @At("/list")
    @POST
    public Object list(Article article, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<Article> articleList = articleService.query(article, pager);
        return layuiTable(articleList, articleService.count(article));
    }

    // 写文章
    @At("/edit")
    @Ok("jsp:template/auth/article/edit")
    public Object edit() {
        Map<String, Object> map = new HashMap<>();
        map.put("labelList", labelService.query());
        return map;
    }

    // 更新文章
    @At("/edit/?")
    @Ok("jsp:template/auth/article/edit")
    public Object edit(long articleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("labelList", labelService.query());
        map.put("article", articleService.fetchLinks(articleId));
        return map;
    }

    // 图片接口
    @At("/upload")
    @POST
    @Ok("raw:json")
    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:upload"})
    public Object upload(HttpSession session, @Param("file") TempFile tempFile) throws IOException {
        String path = "../webapps/Blog_System/resources/article";
        uploads(path, tempFile);
        return uploads(path, tempFile);
    }

    // 发表/更新文章
    @At
    @POST
    public Result edit(Article article) {
        if (articleService.fetch(article.getArticleId()) != null) {
            articleService.update(article);
        } else {
            articleService.insert(article);
        }
        return ajaxSuccess("文章发表成功！");
    }

    // 删除文章
    @At
    @POST
    public Result delete(long articleId) {
        if (articleService.fetch(articleId) == null) {
            return ajaxError("该文章不存在");
        }
        int i = articleService.delete(articleId);
        return ajaxSuccess("删除成功");
    }

    // 删除文章
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result deleteList(List<Article> articleList) {
        int i = articleService.delete(articleList);
        return ajaxSuccess("删除成功");
    }

}
