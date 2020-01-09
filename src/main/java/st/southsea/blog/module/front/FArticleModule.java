//package st.southsea.blog.module.front;
//
//import org.nutz.dao.pager.Pager;
//import org.nutz.ioc.loader.annotation.Inject;
//import org.nutz.ioc.loader.annotation.IocBean;
//import org.nutz.mvc.adaptor.JsonAdaptor;
//import org.nutz.mvc.annotation.*;
//import org.nutz.mvc.filter.CheckSession;
//import org.nutz.mvc.upload.TempFile;
//import org.nutz.mvc.upload.UploadAdaptor;
//import st.southsea.blog.base.Result;
//import st.southsea.blog.base.module.BaseModule;
//import st.southsea.blog.bean.Article;
//import st.southsea.blog.service.ArticleService;
//import st.southsea.blog.service.LabelService;
//import st.southsea.blog.service.UserService;
//
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static st.southsea.blog.module.UploadModule.SAVE;
//
///**
// * @Author: South
// * @Date: 2019-05-13 08:40
// */
//@IocBean
//@At("/")
//@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
//public class FArticleModule extends BaseModule {
//
//    @Inject
//    private ArticleService articleService;
//
//    @Inject
//    private UserService userService;
//
//    @Inject
//    private LabelService labelService;
//
//    // 文章一览
//    @At("/")
//    @Ok("beetl:/auth/article/index.html")
//    public Object index(HttpSession session) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("user", userService.fetch((String) session.getAttribute("user")));
//        map.put("labelList", labelService.query());
//        return map;
//    }
//
//    // 查询所有文章
//    @At("/list")
//    @POST
//    public Object list(Article article, int page, int limit) {
//        Pager pager = new Pager(page, limit);
//        List<Article> articleList = articleService.query(article, pager);
//        return layuiTable(articleList, articleService.count(article));
//    }
//
//    // 写文章
//    @At("/edit")
//    @Ok("beetl:/auth/article/edit.html")
//    public Object edit() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("labelList", labelService.query());
//        return map;
//    }
//
//    // 更新文章
//    @At("/edit/?")
//    @Ok("beetl:/auth/article/edit.html")
//    public Object edit(long articleId) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("labelList", labelService.query());
//        map.put("article", articleService.fetchLinks(articleId));
//        return map;
//    }
//
//    // 图片接口
//    @At("/upload")
//    @POST
//    @Ok("raw:json")
//    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:upload"})
//    public Object upload(HttpSession session, @Param("file") TempFile tempFile) throws IOException, NoSuchAlgorithmException {
//        String path = "../webapps/Blog_System/resources/article";
//        return SAVE(path, tempFile);
//    }
//
//    // 发表/更新文章
//    @At
//    @POST
//    public Result edit(Article article) {
//        if (articleService.fetch(article.getArticleId()) != null) {
//            articleService.update(article);
//        } else {
//            articleService.insert(article);
//        }
//        return ajaxSuccess("文章发表成功");
//    }
//
//    // 删除文章
//    @At
//    @POST
//    public Result delete(long articleId) {
//        if (articleService.fetch(articleId) == null) {
//            return ajaxError("该文章不存在");
//        }
//        int i = articleService.delete(articleId);
//        return ajaxSuccess("删除成功");
//    }
//
//    // 删除文章
//    @At
//    @POST
//    @AdaptBy(type = JsonAdaptor.class)
//    public Result delete(List<Article> articleList) {
//        int i = articleService.delete(articleList);
//        return ajaxSuccess("删除成功");
//    }
//
//}
