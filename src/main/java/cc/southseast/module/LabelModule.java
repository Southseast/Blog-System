package cc.southseast.module;

import cc.southseast.base.Result;
import cc.southseast.base.module.BaseModule;
import cc.southseast.bean.Label;
import cc.southseast.service.ConfigService;
import cc.southseast.service.LabelService;
import cc.southseast.service.UserService;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 08:40
 * @Version 1.0
 */
@IocBean
@At("/auth/label")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class LabelModule extends BaseModule {

    @Inject
    private LabelService labelService;

    @Inject
    private UserService userService;

    @Inject
    private ConfigService configService;

    // 分类一览
    @At("/")
    @Ok("jsp:template/auth/label/index")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) session.getAttribute("user")));
        map.put("config", configService.fetch(1));
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
    @Ok("jsp:template/auth/label/edit")
    public void edit() {
    }

    // 更新分类
    @At("/edit/?")
    @Ok("jsp:template/auth/label/edit")
    public Object edit(long labelId) {
        return labelService.fetch(labelId);
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
        return ajaxSuccess("分类创建成功！");
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
