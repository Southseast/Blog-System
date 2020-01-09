package st.southsea.bean;

import st.southsea.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.*;

import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-04-08 08:43
 * @Version 1.0
 */
@Table("t_article")
public class Article extends BasePojo {

    @Id
    private long articleId;

    private String title;

    @ColDefine(type = ColType.TEXT)
    private String content;

    private long labelId;

    private long browseVolume;

    @One(field = "labelId")
    private Label label;

    @Many(field = "articleId")
    private List<Comment> commentList;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article() {
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public long getBrowseVolume() {
        return browseVolume;
    }

    public void setBrowseVolume(long browseVolume) {
        this.browseVolume = browseVolume;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", labelId=" + labelId +
                ", browseVolume=" + browseVolume +
                ", label=" + label +
                ", commentList=" + commentList +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

}
