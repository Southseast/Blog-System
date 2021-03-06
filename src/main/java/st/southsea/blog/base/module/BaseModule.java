package st.southsea.blog.base.module;

import st.southsea.blog.base.Result;
import org.nutz.lang.util.NutMap;

import java.util.List;

/**
 * @Author Southseast
 * @Date 2019-04-07 10:44
 */
public abstract class BaseModule {

    protected Result ajaxSuccess(String msg) {
        return Result.success(msg);
    }

    protected Result ajaxSuccess(String msg, Object data) {
        return Result.success(msg, data);
    }

    protected Result ajaxError(String msg) {
        return Result.error(msg);
    }

    protected NutMap layuiTable(List<?> list, int count) {
        NutMap re = NutMap.NEW();
        re.put("code", 0);
        re.put("msg", "");
        if (list.size() > 0) {
            re.put("count", count);
            re.put("data", list);
        } else {
            re.put("count", 0);
            re.put("data", null);
        }
        return re;
    }
}
