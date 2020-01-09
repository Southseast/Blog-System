package st.southsea;

import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

/**
 * @Author: Southseast
 * @Date: 2019-04-10 23:11
 * @Version 1.0
 */
@Ok("json")
@Modules()
@SetupBy(value = MainSetup.class)
@IocBy(args = {"*js", "ioc/", "*anno", "st.southsea"})
@Filters(@By(type = CheckSession.class, args = {"user", "/auth/login"}))
@Views({BeetlViewMaker.class})
@Fail("beetl:/error")
public class MainModule {

}
