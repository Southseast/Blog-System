package st.southsea.blog.base.service.impl;

import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.Daos;
import org.nutz.service.EntityService;
import st.southsea.blog.base.service.BaseService;

import java.util.List;

/**
 * @Author Southseast
 * @Date 2019-04-07 10:23
 */
public abstract class BaseServiceImpl<T> extends EntityService<T> implements BaseService<T> {

    public BaseServiceImpl(Dao dao) {
        super(dao);
    }

    public T fetch(int id) {
        return this.dao().fetch(this.getEntityClass(), id);
    }

    public T fetch(String id) {
        return this.dao().fetch(this.getEntityClass(), id);
    }

    public <T> T insert(T obj) {
        return this.dao().insert(obj);
    }

    public <T> T insertOrUpdate(T obj) {
        return this.dao().insertOrUpdate(obj);
    }

    public int update(Object obj) {
        return this.dao().update(obj);
    }

    public int updateIgnoreNull(Object obj) {
        return this.dao().updateIgnoreNull(obj);
    }

    public int delete(int id) {
        return this.dao().delete(this.getEntityClass(), id);
    }

    public int delete(String id) {
        return this.dao().delete(this.getEntityClass(), id);
    }

    public List<T> query(String fieldName, Condition cnd) {
        return Daos.ext(this.dao(), FieldFilter.create(this.getEntityClass(), fieldName)).query(this.getEntityClass(), cnd);
    }

    public List<Record> list(Sql sql) {
        sql.setCallback(Sqls.callback.records());
        this.dao().execute(sql);
        return sql.getList(Record.class);
    }
}
