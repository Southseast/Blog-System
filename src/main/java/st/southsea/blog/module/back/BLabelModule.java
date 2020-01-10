package st.southsea.blog.module.back;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import st.southsea.blog.base.Result;
import st.southsea.blog.base.module.BaseModule;
import st.southsea.blog.bean.Label;
import st.southsea.blog.service.LabelService;
import st.southsea.blog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: South
 * @Date: 2019-05-13 08:40
 */
@IocBean
@At("/auth/label")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class BLabelModule extends BaseModule {

    @Inject
    private LabelService labelService;

    @Inject
    private UserService userService;

    // 主页
    @At("/")
    @Ok("beetl:/auth/label/index.html")
    public void index(HttpSession session) {
    }

    // 表格
    @At("/list")
    @POST
    public Object list(Label label, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<Label> labelList = labelService.query(label, pager);
        return layuiTable(labelList, labelService.count(label));
    }

    // 编辑页面
    @At("/edit/?")
    @Ok("beetl:/auth/label/edit.html")
    public Object edit(long labelId) {
        Map<String, Object> map = new HashMap<>();
        map.put("label", labelService.fetch(labelId));
        return map;
    }

    // 编辑分类
    @At
    @POST
    public Result edit(Label label) {
        if (Lang.isNotEmpty(labelService.fetch(label.getLabelId()))) {
            labelService.update(label);
            return ajaxSuccess("修改成功");
        }
        return ajaxError("修改失败");
    }

    // 添加页面
    @At("/add")
    @Ok("beetl:/auth/label/add.html")
    public void add() {
    }

    // 添加分类
    @At
    @POST
    public Result add(Label label) {
        if (Lang.isEmpty(labelService.fetch(label.getLabelId()))) {
            labelService.insert(label);
            return ajaxSuccess("添加成功");
        }
        return ajaxError("添加失败");
    }

    // 单个删除
    @At
    @POST
    public Result delete(long labelId) {
        labelService.delete(labelId);
        return ajaxSuccess("删除成功");
    }

    // 批量删除
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result batchDel(List<Label> labelList) {
        labelService.delete(labelList);
        return ajaxSuccess("删除成功");
    }
}
