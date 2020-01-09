package st.southsea.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import st.southsea.blog.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.*;

/**
 * @Author: South
 * @Date: 2019-04-08 08:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
