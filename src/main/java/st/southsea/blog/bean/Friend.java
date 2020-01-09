package st.southsea.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;
import st.southsea.blog.base.bean.BasePojo;

/**
 * @Author: South
 * @Date: 2019-04-08 08:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_friend")
public class Friend extends BasePojo {

    @Id
    private long friendId;

    private String nickname;

    private String blogAddress;

}
