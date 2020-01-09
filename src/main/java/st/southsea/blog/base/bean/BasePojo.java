package st.southsea.blog.base.bean;

import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Prev;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: South
 * @Date: 2019-04-08 08:30
 */
public abstract class BasePojo {

    @Prev(els = @EL("$me.now()"))
    protected java.util.Date createTime;

    protected java.util.Date updateTime;

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public java.util.Date now() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());
        ParsePosition pos = new ParsePosition(0);
        return format.parse(date, pos);
    }
}
