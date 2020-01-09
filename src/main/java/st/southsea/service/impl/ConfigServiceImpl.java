package st.southsea.service.impl;

import st.southsea.bean.Config;
import st.southsea.service.ConfigService;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

/**
 * @Author: Southseast
 * @Date: 2019-05-13 17:23
 * @Version 1.0
 */
@IocBean(name = "configService", fields = "dao")
public class ConfigServiceImpl extends IdEntityService<Config> implements ConfigService {

    @Override
    public Config fetch(long id) {
        return dao().fetch(Config.class, id);
    }

    @Override
    public Config insert(Config config) {
        return dao().insert(config);
    }

    @Override
    public int update(Config config) {
        return dao().updateIgnoreNull(config);
    }

}
