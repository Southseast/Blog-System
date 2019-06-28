package cc.southseast;

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
@IocBy(args = {"*js", "./", "*anno", "cc.southseast"})
@Filters(@By(type = CheckSession.class, args = {"user", "/auth/login"}))
@Fail("jsp:template/error")
public class MainModule {

}
