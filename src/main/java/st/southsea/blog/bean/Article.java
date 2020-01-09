package st.southsea.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.*;
import st.southsea.blog.base.bean.BasePojo;

import java.util.List;

/**
 * @Author: South
 * @Date: 2019-04-08 08:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
