package st.southsea.blog.module;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
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
public class LabelModule extends BaseModule {

    @Inject
    private LabelService labelService;

    @Inject
    private UserService userService;

    // 分类一览
    @At("/")
    @Ok("beetl:/auth/label/index.html")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) session.getAttribute("user")));
        return map;
    }

    // 查询所有分类
    @At("/list")
    @POST
    public Object list(Label label, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<Label> labelList = labelService.query(label, pager);
        return layuiTable(labelList, labelService.count(label));
    }

    // 写分类
    @At("/edit")
    @Ok("beetl:/auth/label/edit.html")
    public void edit() {
    }

    // 更新分类
    @At("/edit/?")
    @Ok("beetl:/auth/label/edit.html")
    public Object edit(long labelId) {
        Map<String, Object> map = new HashMap<>();
        map.put("friend", labelService.fetch(labelId));
        return map;
    }

    // 发表/更新分类
    @At
    @POST
    public Result edit(Label label) {
        if (labelService.fetch(label.getLabelId()) != null) {
            labelService.update(label);
        } else {
            labelService.insert(label);
        }
        return ajaxSuccess("分类创建成功");
    }

    // 删除分类
    @At
    @POST
    public Result delete(long labelId) {
        if (labelService.fetch(labelId) == null) {
            return ajaxError("该分类不存在");
        }
        int i = labelService.delete(labelId);
        return ajaxSuccess("删除成功");
    }

    // 删除分类
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result deleteList(List<Label> labelList) {
        int i = labelService.delete(labelList);
        return ajaxSuccess("删除成功");
    }


}
