package st.southsea.module;

import st.southsea.base.Result;
import st.southsea.base.module.BaseModule;
import st.southsea.bean.Config;
import st.southsea.service.ConfigService;
import st.southsea.service.UserService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.nutz.lang.Files.copyFile;

/**
 * @Author: Southseast
 * @Date: 2019-05-14 22:59
 * @Version 1.0
 */
@IocBean
@At("/auth/config")
@Filters(@By(type = CheckSession.class, args = {"admin", "/auth/login"}))
public class ConfigModule extends BaseModule {

    @Inject
    private UserService userService;

    @Inject
    private ConfigService configService;

    @At("")
    @Ok("beetl:/auth/config/index.html")
    public Object index(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) session.getAttribute("user")));
        map.put("config", configService.fetch(1));
        return map;
    }

    // 用户更新接口
    @At
    @POST
    public Result edit(HttpSession session, Config config) throws IOException {

        config.setId(1);

        String filename = "avatar.png";

        String path = "../webapps/Blog_System/resources/static/images/";

        File file = new File(config.getAvatarUrl());

        File avatar = new File(path + "/" + filename);

        config.setAvatarUrl(avatar.getPath().replace("../webapps/Blog_System", ""));

        copyFile(file, avatar);

        configService.update(config);

        return ajaxSuccess("更新成功！");
    }


}
