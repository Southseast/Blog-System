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
import st.southsea.blog.bean.Friend;
import st.southsea.blog.bean.Label;
import st.southsea.blog.service.FriendService;
import st.southsea.blog.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: South
 * @Date: 2019-05-22 09:24
 */
@IocBean
@At("/auth/friend")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class BFriendModule extends BaseModule {

    @Inject
    private FriendService friendService;

    @Inject
    private UserService userService;

    // 主页
    @At("/")
    @Ok("beetl:/auth/friend/index.html")
    public void index(HttpSession session) {
    }

    // 表格
    @At("/list")
    @POST
    public Object list(Friend friend, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<Friend> articleList = friendService.query(friend, pager);
        return layuiTable(articleList, friendService.count(friend));
    }

    // 编辑页面
    @At("/edit/?")
    @Ok("beetl:/auth/friend/edit.html")
    public Object edit(long friendId) {
        Map<String, Object> map = new HashMap<>();
        map.put("friend", friendService.fetch(friendId));
        return map;
    }

    // 编辑好友
    @At
    @POST
    public Result edit(Friend friend) {
        if (Lang.isNotEmpty(friendService.fetch(friend.getFriendId()))) {
            friendService.update(friend);
            return ajaxSuccess("修改成功");
        }
        return ajaxError("修改失败");
    }

    // 添加页面
    @At("/add")
    @Ok("beetl:/auth/friend/add.html")
    public void add() {
    }

    // 添加分类
    @At
    @POST
    public Result add(Friend friend) {
        if (Lang.isEmpty(friendService.fetch(friend.getFriendId()))) {
            friendService.insert(friend);
            return ajaxSuccess("添加成功");
        }
        return ajaxError("添加失败");
    }

    // 单个删除
    @At
    @POST
    public Result delete(long friendId) {
        friendService.delete(friendId);
        return ajaxSuccess("删除成功");
    }

    // 批量删除
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result batchDel(List<Friend> friendList) {
        friendService.delete(friendList);
        return ajaxSuccess("删除成功");
    }
}
