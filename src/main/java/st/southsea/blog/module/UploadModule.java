package st.southsea.blog.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;
import st.southsea.blog.utils.MD5Util;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.nutz.lang.Files.move;

/**
 * @Author: South
 * @Date: 2019-05-27 10:44
 */
@IocBean
@At("/upload")
public class UploadModule {

    public static Object SAVE(String path, TempFile tempFile) throws IOException, NoSuchAlgorithmException {
        String frontUrl = "/resources/images";
        String frontPath = Thread.currentThread().getContextClassLoader().getResource("").getPath().replace("/WEB-INF/classes", frontUrl);
        File dir = new File(frontPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        byte[] randKey = R.sg(24).next().getBytes();
        String suffix = "." + tempFile.getFile().getName().substring(tempFile.getFile().getName().lastIndexOf(".") + 1);
        String filename = MD5Util.Generate(tempFile.getFile().getName() + new Date((new java.util.Date()).getTime()) + randKey.toString()) + suffix;
        File file = new File(frontPath + "/" + path + "/" + filename);
        move(tempFile.getFile(), file);
        NutMap re = NutMap.NEW();
        re.setv("src", frontUrl + "/" + path + "/" + filename);
        return re;
    }

    // 更新接口
    @At("/")
    @POST
    @Ok("raw:json")
    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:upload"})
    public Object upload(HttpSession session, @Param("file") TempFile tempFile) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "success!");
        Map<String, Object> data = new HashMap<>();
        data.put("src", tempFile.getFile().getAbsolutePath());
        map.put("data", data);
        return Json.toJson(map);
    }

    // 更新头像接口
    @At("/avatar")
    @POST
    @Ok("raw:json")
    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:upload"})
    public Object avatar(HttpSession session, @Param("file") TempFile tempFile) throws IOException, NoSuchAlgorithmException {
        NutMap re = NutMap.NEW();
        re.setv("code", 0);
        re.setv("msg", "success!");
        re.setv("data", SAVE("avatar", tempFile));
        return Json.toJson(re);
    }

    // 更新评论接口
    @At("/comment")
    @POST
    @Ok("raw:json")
    @AdaptBy(type = UploadAdaptor.class, args = {"ioc:upload"})
    public Object comment(HttpSession session, @Param("file") TempFile tempFile) throws IOException, NoSuchAlgorithmException {
        NutMap re = NutMap.NEW();
        re.setv("code", 0);
        re.setv("msg", "success!");
        re.setv("data", SAVE("comment", tempFile));
        return Json.toJson(re);
    }
}
