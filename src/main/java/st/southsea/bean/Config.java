package st.southsea.bean;

import st.southsea.base.bean.BasePojo;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @Author: Southseast
 * @Date: 2019-05-24 15:36
 * @Version 1.0
 */
@Table("sys_config")
public class Config extends BasePojo {

    @Id
    private long id;
    private String blogName = "Blog";
    private String avatarUrl = "/resources/static/images/avatar.png";
    private String footer;

    public Config(long id, String blogName, String footer) {
        this.id = id;
        this.blogName = blogName;
        this.footer = footer;
    }

    public Config() {
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id=" + id +
                ", blogName='" + blogName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

}
