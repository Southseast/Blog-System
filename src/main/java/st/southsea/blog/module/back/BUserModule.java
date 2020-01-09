package st.southsea.blog.module.back;

import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.repo.Base64;
import st.southsea.blog.base.Result;
import st.southsea.blog.base.module.BaseModule;
import st.southsea.blog.bean.User;
import st.southsea.blog.service.UserService;
import st.southsea.blog.utils.MD5Util;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: South
 * @Date: 2019-05-14 22:59
 */
@IocBean
@At("/auth/user")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class BUserModule extends BaseModule {

    @Inject
    private UserService userService;

    // 主页
    @At("/")
    @Ok("beetl:/auth/user/index.html")
    public void index(HttpSession session) {
    }

    // 表格
    @At
    @POST
    public Object list(User user, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<User> userList = userService.query(user, pager);
        return layuiTable(userList, userService.count(user));
    }

    // 编辑页面
    @At("/edit/?")
    @Ok("beetl:/auth/user/edit.html")
    @Filters(@By(type = CheckSession.class, args = {"user", "/auth/login"}))
    public Object edit(String email, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch(new String(Base64.decode(email))));
        return map;
    }

    // 编辑用户
    @At
    @POST
    public Result edit(User user, HttpSession session) throws NoSuchAlgorithmException {
        User originalUser = userService.fetch((String) session.getAttribute("user"));
        // 判断评论文章是否存在
        if (Lang.isNotEmpty(userService.fetch(user.getEmail()))) {
            // 判断是否为管理员
            if (originalUser.getPermissionId() == 1) {
                if (Lang.isNotEmpty(user.getPassword())){
                    user.setPassword(MD5Util.Generate(user.getPassword()));
                }
                userService.update(user);
                return ajaxSuccess("修改成功");
            } else {
                if (originalUser.getEmail().equals(user.getEmail())) {
                    if (Lang.isNotEmpty(user.getPassword())){
                        user.setPassword(MD5Util.Generate(user.getPassword()));
                    }
                    userService.update(user);
                    return ajaxSuccess("修改成功");
                }
            }
        }
        return ajaxError("修改失败");
    }

    // 添加页面
    @At("/add")
    @Ok("beetl:/auth/user/add.html")
    public void add(HttpSession session) {
    }

    // 添加用户
    @At
    @POST
    public Result add(User user) throws NoSuchAlgorithmException {
        if (Lang.isEmpty(userService.fetch(user.getEmail()))) {
            user.setPassword(MD5Util.Generate(user.getPassword()));
            userService.insert(user);
            return ajaxSuccess("添加成功");
        }
        return ajaxError("添加失败");
    }

    // 单个删除
    @At
    @POST
    public Result delete(String email) {
        userService.delete(email);
        return ajaxSuccess("删除成功");
    }

    // 批量删除
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result delete(List<User> userList) {
        userService.delete(userList);
        return ajaxSuccess("删除成功");
    }
}
