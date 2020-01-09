package st.southsea.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import st.southsea.blog.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * @Author: South
 * @Date: 2019-04-08 08:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_label")
public class Label extends BasePojo {

    @Id
    private long labelId;

    private String labelName;

    @Many(field = "labelId")
    private List<Article> articleList;

}
