package st.southsea.blog.service.impl;

import st.southsea.blog.bean.Friend;
import st.southsea.blog.service.FriendService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.IdEntityService;

import java.util.List;

/**
 * @Author: South
 * @Date: 2019-05-13 17:23
 */
@IocBean(name = "friendService", fields = "dao")
public class FriendServiceImpl extends IdEntityService<Friend> implements FriendService {

    @Override
    public int count(Friend friend) {
        return this.query(friend, null).size();
    }

    @Override
    public Friend insert(Friend friend) {
        return dao().insert(friend);
    }

    @Override
    public int delete(List<Friend> friendList) {
        return dao().delete(friendList);
    }

    @Override
    public int update(Friend friend) {
        return dao().update(friend);
    }

    @Override
    public List<Friend> query(Friend friend, Pager pager) {
        Cnd cnd = Cnd.NEW();
        if (Lang.isNotEmpty(friend.getNickname())) {
            cnd.and("nickname", "like", "%" + friend.getNickname() + "%");
        }
        if (Lang.isNotEmpty(friend.getBlogAddress())) {
            cnd.and("blogAddress", "like", "%" + friend.getBlogAddress() + "%");
        }
        cnd.asc("friendId");
        return pager == null ? dao().query(Friend.class, cnd) : dao().query(Friend.class, cnd, pager);
    }
}
