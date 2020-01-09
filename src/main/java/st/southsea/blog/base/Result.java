package st.southsea.blog.base;

import org.nutz.lang.Strings;
import org.nutz.mvc.Mvcs;

/**
 * @Author Southseast
 * @Date 2019-04-12 10:40
 */
public class Result {

    private boolean ok;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(boolean ok, String msg, Object data) {
        this.ok = ok;
        if (Strings.isBlank(msg) || Mvcs.getActionContext() == null || Mvcs.getActionContext().getRequest() == null || Mvcs.getMessage(Mvcs.getActionContext().getRequest(), msg) == null) {
            this.msg = Strings.sNull(msg);
        } else {
            this.msg = Mvcs.getMessage(Mvcs.getActionContext().getRequest(), msg);
        }
        this.data = data;
    }

    public static Result NEW() {
        return new Result();
    }

    public static Result success(String msg) {
        return new Result(true, msg, null);
    }

    public static Result success(String msg, Object data) {
        return new Result(true, msg, data);
    }

    public static Result error(String msg) {
        return new Result(false, msg, null);
    }

    public Result addMsg(String msg) {
        if (Strings.isBlank(msg) || Mvcs.getActionContext() == null || Mvcs.getActionContext().getRequest() == null || Mvcs.getMessage(Mvcs.getActionContext().getRequest(), msg) == null) {
            this.msg = "";
        } else {
            this.msg = Mvcs.getMessage(Mvcs.getActionContext().getRequest(), msg);
        }
        return this;
    }

    public Result addData(Object data) {
        this.data = data;
        return this;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
