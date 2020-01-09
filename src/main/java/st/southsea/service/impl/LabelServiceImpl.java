package st.southsea.service.impl;

import st.southsea.bean.Label;
import st.southsea.service.LabelService;
import org.nutz.dao.Cnd;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Lang;
import org.nutz.service.IdEntityService;

import java.util.List;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 17:23
 * @Version 1.0
 */
@IocBean(name = "labelService", fields = "dao")
public class LabelServiceImpl extends IdEntityService<Label> implements LabelService {

    @Override
    public int count(Label label) {
        return this.query(label, null).size();
    }

    @Override
    public Label fetch(long labelId) {
        return dao().fetch(Label.class, labelId);
    }

    @Override
    public Label insert(Label label) {
        return dao().insert(label);
    }

    @Override
    public int delete(List<Label> labelList) {
        return dao().delete(labelList);
    }

    @Override
    public int update(Label label) {
        return dao().updateIgnoreNull(label);
    }

    @Override
    public List<Label> query(Label label, Pager pager) {
        Cnd cnd = Cnd.NEW();
        if (Lang.isNotEmpty(label.getLabelName())) {
            cnd.and("labelName", "like", "%" + label.getLabelName() + "%");
        }
        cnd.asc("labelId");
        return pager == null ? dao().query(Label.class, cnd) : dao().query(Label.class, cnd, pager);
    }
}
