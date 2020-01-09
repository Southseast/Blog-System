package st.southsea.base.bean;

import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Prev;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @Author: Southseast
 * @Date: 2019-04-08 08:30
 * @Version 1.0
 */
public abstract class BasePojo {

    @Prev(els = @EL("$me.create()"))
    protected Date createTime;

    @Prev(els = @EL("$me.update()"))
    protected Date updateTime;

    public String getCreateMD() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(this.createTime);
    }

    public int getCreateY() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(simpleDateFormat.format(this.createTime));
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date create() {
        if (this.createTime == null)
            return new Date((new java.util.Date()).getTime());
        else
            return this.createTime;
    }

    public Date update() {
        return new Date((new java.util.Date()).getTime());
    }
}
