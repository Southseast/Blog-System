package cc.southseast.bean;

import cc.southseast.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author: Southseast
 * @Date: 2019-04-08 08:38
 * @Version 1.0
 */
@Table("t_friend")
public class Friend extends BasePojo {

    @Id
    private long friendId;

    private String nickname;

    private String blogAddress;

    public Friend() {
    }

    public Friend(long friendId, String nickname, String blogAddress) {
        this.friendId = friendId;
        this.nickname = nickname;
        this.blogAddress = blogAddress;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "friendId=" + friendId +
                ", nickname='" + nickname + '\'' +
                ", blogAddress='" + blogAddress + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
