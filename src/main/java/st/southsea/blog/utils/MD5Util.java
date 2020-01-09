package st.southsea.blog.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: South
 * @Date: 2019-05-20 15:11
 */
public class MD5Util {

    // 生成
    public static String Generate(String plainText) throws NoSuchAlgorithmException {
        //定义一个字节数组
        byte[] secretBytes = null;
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        //对字符串进行加密
        md.update(plainText.getBytes());
        //获得加密后的数据
        secretBytes = md.digest();
        //将加密后的数据转换为16进制数字
        String res = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - res.length(); i++) {
            res = "0" + res;
        }
        return res;
    }

    // 验证
    public static Boolean Verify(String plainText, String originalCiphertext) throws NoSuchAlgorithmException {
        String newCiphertext = MD5Util.Generate(plainText);
        if (newCiphertext.equalsIgnoreCase(originalCiphertext)) {
            return true;
        }
        return false;
    }


}
