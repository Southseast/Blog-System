package cc.southseast.module;

import cc.southseast.base.Result;
import cc.southseast.base.module.BaseModule;
import cc.southseast.bean.Friend;
import cc.southseast.service.ConfigService;
import cc.southseast.service.FriendService;
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
 * @Date: 2019-05-22 09:24
 * @Version 1.0
 */
@IocBean
@At("/auth/friend")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class FriendModule extends BaseModule {

    @Inject
    private FriendService friendService;


    @Inject
    private UserService userService;

    @Inject
    private ConfigService configService;

    // 好友一览
    @At("/")
    @Ok("jsp:template/auth/friend/index")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) session.getAttribute("user")));
        map.put("config", configService.fetch(1));
        return map;
    }

    // 查询所有好友
    @At("/list")
    @POST
    public Object list(Friend friend, int page, int limit) {
        Pager pager = new Pager(page, limit);
        List<Friend> articleList = friendService.query(friend, pager);
        return layuiTable(articleList, friendService.count(friend));
    }

    // 好友编辑
    @At("/edit")
    @Ok("jsp:template/auth/friend/edit")
    public void edit() {
    }

    // 好友修改
    @At("/edit/?")
    @Ok("jsp:template/auth/friend/edit")
    public Friend edit(long friendId) {
        return friendService.fetch(friendId);
    }

    // 增加/修改好友
    @At
    @POST
    public Result edit(Friend friend) {
        if (friendService.fetch(friend.getFriendId()) != null) {
            friendService.update(friend);
        } else {
            friendService.insert(friend);
        }
        return ajaxSuccess("好友增改成功！");
    }

    // 删除好友
    @At
    @POST
    public Result delete(long friendId) {
        if (friendService.fetch(friendId) == null) {
            return ajaxError("该好友不存在");
        }
        int i = friendService.delete(friendId);
        return ajaxSuccess("删除成功");
    }

    // 删除好友
    @At
    @POST
    @AdaptBy(type = JsonAdaptor.class)
    public Result deleteList(List<Friend> friendList) {
        int i = friendService.delete(friendList);
        return ajaxSuccess("删除成功");
    }

}
