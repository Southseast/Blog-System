package st.southsea.blog.utils;

import org.nutz.json.Json;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.TempFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.nutz.lang.Files.copyFile;


/**
 * @Author: South
 * @Date: 2019/12/11 11:29 下午
 */
public class UploadUtil {

    public static Object UPLOAD(String path, @Param("file") TempFile tempFile) throws IOException {
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
}
