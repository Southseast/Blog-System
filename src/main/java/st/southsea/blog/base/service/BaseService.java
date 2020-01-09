package st.southsea.blog.base.service;

import org.nutz.dao.Condition;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;

import java.util.List;

/**
 * @Author Southseast
 * @Date 2019-11-06 16:30
 */
public interface BaseService<T> {

    /**
     * 统计数据条数
     *
     * @param []
     * @return int
     * @Author Southseast
     * @Date 2019-11-07 10:30
     */
    int count();

    /**
     * 统计符合条件的数据条数
     *
     * @param [cnd]
     * @return int
     * @Author Southseast
     * @Date 2019-11-07 10:30
     */
    int count(Condition cnd);

    /***
     * 根据 int 型主键查找
     *
     * @Author Southseast
     * @Date 2019-11-07 09:58
     * @param [id]
     * @return T
     */
    T fetch(int id);

    /**
     * 根据 String 型主键查找
     *
     * @param [id]
     * @return T
     * @Author Southseast
     * @Date 2019-11-07 09:58
     */
    T fetch(String id);

    /**
     * 查找符合条件的第一条记录
     *
     * @param [cnd]
     * @return T
     * @Author Southseast
     * @Date 2019-11-07 10:15
     */
    T fetch(Condition cnd);

    /**
     * 将一个对象插入数据库
     *
     * @param [obj]
     * @return T
     * @Author Southseast
     * @Date 2019-11-07 10:15
     */
    <T> T insert(T obj);

    /**
     * 根据对象的主键(@Id/@Name/@Pk)先查询, 如果存在就更新, 不存在就插入
     *
     * @param [obj]
     * @return T
     * @Author Southseast
     * @Date 2019-11-07 10:15
     */
    <T> T insertOrUpdate(T obj);

    /**
     * 更新数据
     *
     * @param [obj]
     * @return int
     * @Author Southseast
     * @Date 2019-11-07 10:15
     */
    int update(Object obj);

    /**
     * 更新数据并忽略值为null的字段
     *
     * @param [obj]
     * @return int
     * @Author Southseast
     * @Date 2019-11-07 10:14
     */
    int updateIgnoreNull(Object obj);

    /**
     * 通过 int 型主键删除数据
     *
     * @param [id]
     * @return int
     * @Author Southseast
     * @Date 2019-11-07 10:36
     */
    int delete(int id);

    /**
     * 通过 String 型主键删除数据
     *
     * @param [id]
     * @return int
     * @Author Southseast
     * @Date 2019-11-07 10:36
     */
    int delete(String id);

    /**
     * 获取全部数据
     *
     * @param []
     * @return java.util.List<T>
     * @Author Southseast
     * @Date 2019-11-07 10:14
     */
    List<T> query();

    /**
     * 根据条件查找一组数据
     *
     * @param [cnd]
     * @return java.util.List<T>
     * @Author Southseast
     * @Date 2019-11-07 10:14
     */
    List<T> query(Condition cnd);

    /**
     * 根据条件查询数据的部分字段
     *
     * @param [fieldName, cnd]
     * @return java.util.List<T>
     * @Author Southseast
     * @Date 2019-11-07 10:14
     */
    List<T> query(String fieldName, Condition cnd);

    /**
     * 分页查询
     *
     * @param [cnd, pager]
     * @return java.util.List<T>
     * @Author Southseast
     * @Date 2019-11-07 10:14
     */
    List<T> query(Condition cnd, Pager pager);

    /**
     * 自定义 SQL 返回 Record 记录集
     *
     * @param [sql]
     * @return java.util.List<org.nutz.dao.entity.Record>
     * @Author Southseast
     * @Date 2019-11-07 09:58
     */
    List<Record> list(Sql sql);
}
