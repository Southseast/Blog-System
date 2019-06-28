package cc.southseast.service;

import cc.southseast.bean.Config;

/**
 * @author Southseast
 * @date 2018-5-13 11:01
 * @desc
 */
public interface ConfigService {

    Config fetch(long id);

    Config insert(Config config);

    int update(Config config);
}
