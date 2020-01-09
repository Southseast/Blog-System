package st.southsea.service.impl;

import st.southsea.bean.Article;
import st.southsea.bean.Comment;
import st.southsea.service.ArticleService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.IdEntityService;

import java.util.Collections;
import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 17:23
 * @Version 1.0
 */
@IocBean(name = "articleService", fields = "dao")
public class ArticleServiceImpl extends IdEntityService<Article> implements ArticleService {

    @Override
    public int count(Article article) {
        return this.query(article, null).size();
    }

    @Override
    public Article fetch(long articleId) {
        return dao().fetch(Article.class, articleId);
    }

    @Override
    public Article fetchLinks(long articleId) {
        Article article = dao().fetchLinks(dao().fetchLinks(dao().fetch(Article.class, articleId), "commentList"), "label");
        Collections.reverse(article.getCommentList());
        for (Comment comment : article.getCommentList()) {
            comment.setUser(dao().fetchLinks(dao().fetch(Comment.class, comment.getCommentId()), "user").getUser());
        }
        return article;
    }

    @Override
    public Article insert(Article article) {
        return dao().insert(article);
    }

    @Override
    public int delete(List<Article> articleList) {
        return dao().delete(articleList);
    }

    @Override
    public int update(Article article) {
        return dao().updateIgnoreNull(article);
    }

    @Override
    public List<Article> query(Article article, Pager pager) {
        Cnd cnd = Cnd.NEW();
        if (Lang.isNotEmpty(article.getTitle())) {
            cnd.and("title", "like", "%" + article.getTitle() + "%");
        }
        if (Lang.isNotEmpty(article .getContent())) {
            cnd.and("content", "like", "%" + article.getContent() + "%");
        }
        if (article.getLabelId() != 0) {
            cnd.and("t_article.labelId", "=", article.getLabelId());
        }
        if (Lang.isNotEmpty(article.getCreateTime()) && Lang.isNotEmpty(article.getUpdateTime())) {
            cnd.and("t_article.createTime", "between", new String[]{String.valueOf(article.getCreateTime()), String.valueOf(article.getUpdateTime())});
        }
        cnd.desc("t_article.createTime").desc("articleId");
        return pager == null ? dao().queryByJoin(Article.class, null, cnd) : dao().queryByJoin(Article.class, null, cnd, pager);
    }

    @Override
    public List<Article> queryFormSearch(Article article, Pager pager) {
        Cnd cnd = Cnd.NEW();
        if (Lang.isNotEmpty(article.getContent())) {
            cnd.and("content", "like", "%" + article.getContent() + "%").and("title", "like", "%" + article.getContent() + "%");
        }
        cnd.desc("t_article.createTime").desc("articleId");
        return pager == null ? dao().queryByJoin(Article.class, null, cnd) : dao().queryByJoin(Article.class, null, cnd, pager);
    }

    @Override
    public List<Article> query() {
        Cnd cnd = Cnd.NEW();
        cnd.desc("createTime");
        return dao().query(Article.class, cnd);
    }
}
