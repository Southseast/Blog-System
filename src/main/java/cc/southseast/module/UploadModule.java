package cc.southseast.module;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.nutz.lang.Files.copyFile;

/**
 * @Author: Southseast
 * @Date: 2019-05-27 10:44
 * @Version 1.0
 */
@IocBean
@At("/upload")
public class UploadModule {

    public static Object uploads(String path, @Param("file") TempFile tempFile) throws IOException {
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(path + "/" + tempFile.getFile().getName());
        copyFile(tempFile.getFile(), file);

        Map<String, Object> map = new HashMap<>();

        map.put("file_path", file.getPath().replace("../webapps/Blog_System", ""));
        return Json.toJson(map);
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
}
