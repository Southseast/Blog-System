package cc.southseast.service;

import cc.southseast.bean.Friend;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * @author Southseast
 * @date 2018-5-13 12:01
 * @desc
 */
public interface FriendService {

    int count();

    int count(Friend friend);

    Friend fetch(long friendId);

    Friend insert(Friend friend);

    int delete(long friendId);

    int delete(List<Friend> friendList);

    int update(Friend friend);

    List<Friend> query();

    List<Friend> query(Friend friend, Pager pager);
}
