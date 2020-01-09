package st.southsea.blog.utils;

import java.util.Calendar;
import java.util.Random;

import static st.southsea.blog.utils.DataUtil.*;

/**
 * @Author: South
 * @Date: 2020/1/1 10:35 上午
 * 生成随机数据
 */
public class RandomUtil {

    private static Random RAND = new Random();

    // 根据性别随机生成姓名
    public static String GENERATE_NAME(int sex) {
        StringBuilder name = new StringBuilder();
        int length = RAND.nextInt(2) + 1;
        name.append(FIRST_NAME.charAt(Math.abs(RAND.nextInt()) % FIRST_NAME.length()));
        for (int i = 0; i < length; i++) {
            if (sex == 2)
                name.append(GRIL_NAME.charAt(Math.abs(RAND.nextInt()) % GRIL_NAME.length()));
            else
                name.append(BOY_NAME.charAt(Math.abs(RAND.nextInt()) % BOY_NAME.length()));

        }
        return name.toString();
    }

    // 随机生成数字
    public static String GENERATE_NUM(int length) {
        StringBuilder no = new StringBuilder();
        for (int j = 0; j < length; j++) {
            no.append(RAND.nextInt(10));
        }
        return no.toString();
    }

    // 随机生成工号
    public static String GENERATE_NO(int length) {
        StringBuilder no = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR) - RAND.nextInt(4);
        no.append(year);
        return no.toString() + GENERATE_NUM(length - 4);
    }

    // 随机生成密码
    public static String GENERATE_PASSWORD(int length) {
        StringBuilder no = new StringBuilder();
        for (int j = 0; j < length; j++) {
            no.append(RAND.nextInt(10));
        }
        return no.toString();
    }

    // 随机生成邮箱
    public static String GENERATE_EMAIL() {
        StringBuilder email = new StringBuilder();
        email.append(RAND.nextInt(9) + 1);
        for (int j = 0; j < 8; j++) {
            email.append(RAND.nextInt(10));
        }
        email.append("@qq.com");
        return email.toString();
    }

    // 随机生成性别
    public static int GENERATE_SEX() {
        return RAND.nextInt(2) + 1;
    }

    // 随机生成电话
    public static String GENERATE_PHONE() {
        return PHONE_HEAD.get(RAND.nextInt(PHONE_HEAD.size())) + GENERATE_NUM(8);
    }

    // 随机生成学校
    public static Long GENERATE_SCHOOL() {
        Long[] keys = SCHOOL_MAP.keySet().toArray(new Long[0]);
        return keys[RAND.nextInt(keys.length)];
    }

    // 随机生成语句
    public static String GENERATE_WORD() {
        return WORDS.get(RAND.nextInt(WORDS.size()));
    }

}
