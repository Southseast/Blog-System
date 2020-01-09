package st.southsea.service.impl;

import st.southsea.bean.User;
import st.southsea.service.UserService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.NameEntityService;

import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 17:23
 * @Version 1.0
 */
@IocBean(name = "userService", fields = "dao")
public class UserServiceImpl extends NameEntityService<User> implements UserService {

    @Override
    public int count(User user) {
        return this.query(user, null).size();
    }

    public User fetch(String email) {
        return dao().fetchLinks(dao().fetch(User.class, email), "commentList");
    }

    @Override
    public User getHost() {
        Cnd cnd = Cnd.NEW();
        cnd.and("permissionId", "=", "1");
        return dao().query(User.class, cnd).get(0);
    }

    public User insert(User user) {
        return dao().insert(user);
    }

    @Override
    public int update(User user) {
        return dao().updateIgnoreNull(user);
    }

    @Override
    public int delete(List<User> userList) {
        return dao().delete(userList);
    }

    @Override
    public List<User> query(User user, Pager pager) {
        Cnd cnd = Cnd.NEW();
        if (Lang.isNotEmpty(user.getEmail())) {
            cnd.and("email", "like", "%" + user.getEmail() + "%");
        }
        if (Lang.isNotEmpty(user.getNickname())) {
            cnd.and("nickname", "like", "%" + user.getNickname() + "%");
        }
        if (user.getSex() != 0) {
            cnd.and("sex", "=", user.getSex());
        }
        if (Lang.isNotEmpty(user.getTelphone())) {
            cnd.and("telphone", "like", "%" + user.getTelphone() + "%");
        }
        if (Lang.isNotEmpty(user.getRegion())) {
            cnd.and("region", "like", "%" + user.getRegion() + "%");
        }
        if (Lang.isNotEmpty(user.getOccupation())) {
            cnd.and("occupation", "like", "%" + user.getOccupation() + "%");
        }
        if (Lang.isNotEmpty(user.getSchool())) {
            cnd.and("school", "like", "%" + user.getSchool() + "%");
        }
        if (Lang.isNotEmpty(user.getSignature())) {
            cnd.and("signature", "like", "%" + user.getSignature() + "%");
        }
        if (Lang.isNotEmpty(user.getBlogAddress())) {
            cnd.and("blogAddress", "like", "%" + user.getBlogAddress() + "%");
        }
        if (Lang.isNotEmpty(user.getGithubAddress())) {
            cnd.and("githubAddress", "like", "%" + user.getGithubAddress() + "%");
        }
        if (Lang.isNotEmpty(user.getCreateTime()) && Lang.isNotEmpty(user.getUpdateTime())) {
            cnd.and("createTime", "between", new String[]{String.valueOf(user.getCreateTime()), String.valueOf(user.getUpdateTime())});
        }
        cnd.and("permissionId", "!=", "1").asc("createTime");
        return pager == null ? dao().query(User.class, cnd) : dao().query(User.class, cnd, pager);
    }

}
