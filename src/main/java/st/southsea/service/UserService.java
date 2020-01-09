package st.southsea.service;

import st.southsea.bean.User;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * @author Southseast
 * @date 2018-5-13 11:01
 * @desc
 */
public interface UserService {

    int count();

    int count(User user);

    User fetch(String email);

    User getHost();

    User insert(User user);

    int update(User user);

    int delete(String email);

    int delete(List<User> userList);

    List<User> query();

    List<User> query(User user, Pager pager);
}
