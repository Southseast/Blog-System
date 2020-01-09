package st.southsea.blog.utils;

import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: South
 * @Date: 2019-06-02 23:11
 */

public class DESUtil {

    public static final Log log = Logs.get();
    private static final String Iv = "\0\0\0\0\0\0\0\0";
    private static final String Transformation = "DESede/CBC/PKCS5Padding";

    public static String _3DES_Encode(byte[] key, byte[] data) {
        SecretKey deskey = new SecretKeySpec(key, "DESede");
        IvParameterSpec iv = new IvParameterSpec(Iv.getBytes());
        try {
            Cipher c1 = Cipher.getInstance(Transformation);
            c1.init(Cipher.ENCRYPT_MODE, deskey, iv);
            byte[] re = c1.doFinal(data);
            return Lang.fixedHexString(re);
        } catch (Exception e) {
            log.info("3DES FAIL?", e);
            e.printStackTrace();
        }
        return null;
    }

    public static String _3DES_Decode(byte[] key, byte[] data) {
        SecretKey deskey = new SecretKeySpec(key, "DESede");
        IvParameterSpec iv = new IvParameterSpec(Iv.getBytes());
        try {
            Cipher c1 = Cipher.getInstance(Transformation);
            c1.init(Cipher.DECRYPT_MODE, deskey, iv);
            byte[] re = c1.doFinal(data);
            return new String(re);
        } catch (Exception e) {
            log.debug("BAD 3DES decode", e);
        }
        return null;
    }

    public static NutMap Verify(String token, byte[] emailKEY) {
        NutMap re = new NutMap();
        if (Strings.isBlank(token)) {
            return re.setv("msg", "非法token！");
        }
        String deToken = DESUtil._3DES_Decode(emailKEY, DESUtil.hexstr2bytearray(token));
        if (Strings.isBlank(deToken))
            return re.setv("msg", "非法token！");
        // 获取加密内容
        String[] tmp = deToken.split(",", 2);
        if (tmp.length != 2 || tmp[0].length() == 0 || tmp[1].length() == 0)
            return re.setv("msg", "非法token！");
        // 获取时间
        long time = Long.parseLong(tmp[1]);
        if (System.currentTimeMillis() - time > 10 * 60 * 1000) {
            return re.setv("msg", "非法token！");
        }
        // 获取邮箱
        String email = tmp[0];
        return re.setv("email", email);
    }

    public static byte[] hexstr2bytearray(String str) {
        byte[] re = new byte[str.length() / 2];
        for (int i = 0; i < re.length; i++) {
            int r = Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
            re[i] = (byte) r;
        }
        return re;
    }
}