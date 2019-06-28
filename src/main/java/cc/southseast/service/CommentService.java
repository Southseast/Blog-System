package cc.southseast.service;

import cc.southseast.bean.Comment;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * @author Southseast
 * @date 2018-5-13 11:01
 * @desc
 */
public interface CommentService {

    int count();

    int count(Comment comment);

    Comment fetch(long commentId);

    Comment insert(Comment comment);

    int delete(long commentId);

    int delete(List<Comment> commentList);

    int update(Comment comment);

    List<Comment> query();

    List<Comment> query(Comment comment, Pager pager);
}
