package st.southsea.bean;

import st.southsea.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.*;

/**
 * @Author: Southseast
 * @Date: 2019-04-08 08:41
 * @Version 1.0
 */
@Table("t_comment")
public class Comment extends BasePojo {

    @Id
    private long commentId;

    @ColDefine(type = ColType.TEXT)
    private String content;

    private long articleId;

    @One(field = "articleId")
    private Article article;

    private String email;

    @One(field = "email")
    private User user;

    public Comment() {
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                ", article=" + article +
                ", user=" + user +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
