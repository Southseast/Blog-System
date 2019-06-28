package cc.southseast.service;

import cc.southseast.bean.Article;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * @author Southseast
 * @date 2018-5-13 11:01
 * @desc
 */
public interface ArticleService {

    int count();

    int count(Article article);

    Article fetch(long articleId);

    Article fetchLinks(long articleId);

    Article insert(Article article);

    int delete(long articleId);

    int delete(List<Article> articleList);

    int update(Article article);

    List<Article> query();

    List<Article> query(Article article, Pager pager);

    List<Article> queryFormSearch(Article article, Pager pager);
}
