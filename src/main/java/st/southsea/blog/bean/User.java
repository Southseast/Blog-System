package st.southsea.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import st.southsea.blog.base.bean.BasePojo;

import java.util.List;

/**
 * @Author: South
 * @Date: 2019-04-08 08:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("t_user")
public class User extends BasePojo {

    @Name
    // 邮箱
    private String email;

    // 头像地址
    private String avatarUrl = "/resources/images/init/avatar.png";

    // 昵称
    private String nickname;

    // 密码
    private String password;

    // 性别
    private int sex;

    // 电话
    private String telphone;

    // 地区
    private String region;

    // 工作
    private String occupation;

    // 毕业院校
    private String school;

    // 个性签名
    private String signature;

    // 博客地址
    private String blogAddress;

    // github地址
    private String githubAddress;

    // 权限
    private int permissionId;

    @Many(field = "email")
    private List<Comment> commentList;

}
