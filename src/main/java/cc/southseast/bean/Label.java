package cc.southseast.bean;

import cc.southseast.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-04-08 08:48
 * @Version 1.0
 */
@Table("t_label")
public class Label extends BasePojo {

    @Id
    private long labelId;

    private String labelName;

    @Many(field = "labelId")
    private List<Article> articleList;

    public Label() {
    }

    public long getLabelId() {
        return labelId;
    }

    public void setLabelId(long labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", labelName=" + labelName +
                ", articleList=" + articleList +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
