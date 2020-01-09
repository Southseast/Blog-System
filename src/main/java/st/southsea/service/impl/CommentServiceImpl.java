package st.southsea.service.impl;

import st.southsea.bean.Comment;
import st.southsea.service.CommentService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.IdEntityService;

import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 17:23
 * @Version 1.0
 */
@IocBean(name = "commentService", fields = "dao")
public class CommentServiceImpl extends IdEntityService<Comment> implements CommentService {

    @Override
    public int count(Comment comment) {
        return this.query(comment, null).size();
    }

    @Override
    public Comment fetch(long commentId) {
        return dao().fetch(Comment.class, commentId);
    }

    @Override
    public Comment insert(Comment comment) {
        return dao().insert(comment);
    }

    @Override
    public int delete(List<Comment> commentList) {
        return dao().delete(commentList);
    }

    @Override
    public int update(Comment comment) {
        return dao().updateIgnoreNull(comment);
    }

    @Override
    public List<Comment> query(Comment comment, Pager pager) {
        Cnd cnd = Cnd.NEW();
        // 判断是否为管理员查询
        if (Lang.isNotEmpty(comment.getUser())) {
            cnd.and("t_comment.email", "=", comment.getUser().getEmail());
        }
        if (Lang.isNotEmpty(comment.getContent())) {
            cnd.and("t_comment.content", "like", "%" + comment.getContent() + "%");
        }
        if (Lang.isNotEmpty(comment.getCreateTime()) && Lang.isNotEmpty(comment.getUpdateTime())) {
            cnd.and("t_comment.createTime", "between", new String[]{String.valueOf(comment.getCreateTime()), String.valueOf(comment.getUpdateTime())});
        }
        cnd.desc("t_comment.createTime").desc("commentId");
        return pager == null ? dao().queryByJoin(Comment.class, null, cnd) : dao().queryByJoin(Comment.class, null, cnd, pager);
    }

    @Override
    public List<Comment> query() {
        Cnd cnd = Cnd.NEW();
        cnd.desc("t_comment.createTime").desc("commentId");
        return dao().queryByJoin(Comment.class, null, cnd);
    }
}
