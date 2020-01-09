package st.southsea.bean;

import st.southsea.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-04-08 08:23
 * @Version 1.0
 */
@Table("t_user")
public class User extends BasePojo {

    @Name
    // 邮箱
    private String email;

    // 头像地址
    private String avatarUrl = "/resources/static/images/avatar.png";

    // 昵称
    private String nickname;

    // 密码
    private String password;

    // 性别
    private long sex;

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
    private long permissionId;

    @Many(field = "email")
    private List<Comment> commentList;

    public User() {
    }

    public User(String email, String nickname, String password, long sex, String telphone, String region, String occupation, String school, String signature, String blogAddress, String githubAddress, long permissionId) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.sex = sex;
        this.telphone = telphone;
        this.region = region;
        this.occupation = occupation;
        this.school = school;
        this.signature = signature;
        this.blogAddress = blogAddress;
        this.githubAddress = githubAddress;
        this.permissionId = permissionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSex() {
        return sex;
    }

    public void setSex(long sex) {
        this.sex = sex;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public String getGithubAddress() {
        return githubAddress;
    }

    public void setGithubAddress(String githubAddress) {
        this.githubAddress = githubAddress;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", telphone='" + telphone + '\'' +
                ", region='" + region + '\'' +
                ", occupation='" + occupation + '\'' +
                ", school='" + school + '\'' +
                ", signature='" + signature + '\'' +
                ", blogAddress='" + blogAddress + '\'' +
                ", githubAddress='" + githubAddress + '\'' +
                ", permissionId=" + permissionId +
                ", commentList=" + commentList +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
