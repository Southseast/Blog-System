package cc.southseast.service;

import cc.southseast.bean.Label;
import org.nutz.dao.pager.Pager;

import java.util.List;

/**
 * @author Southseast
 * @date 2018-5-13 11:01
 * @desc
 */
public interface LabelService {

    int count();

    int count(Label label);

    Label fetch(long labelId);

    Label insert(Label label);

    int delete(long labelId);

    int delete(List<Label> labelList);

    int update(Label label);

    List<Label> query();

    List<Label> query(Label label, Pager pager);
}
