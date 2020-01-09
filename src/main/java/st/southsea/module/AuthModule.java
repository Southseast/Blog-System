package st.southsea.module;

import org.nutz.img.Images;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.random.R;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import st.southsea.base.Result;
import st.southsea.base.module.BaseModule;
import st.southsea.bean.Article;
import st.southsea.bean.Comment;
import st.southsea.bean.User;
import st.southsea.service.*;
import st.southsea.utils.DESUtil;
import st.southsea.utils.MD5Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Southseast
 * @Date: 2019-05-22 09:24
 * @Version 1.0
 */
@IocBean
@At("/auth")
public class AuthModule extends BaseModule {

    @Inject
    private UserService userService;

    @Inject
    private FriendService friendService;

    @Inject
    private CommentService commentService;

    @Inject
    private ArticleService articleService;

    @Inject
    private LabelService labelService;

    @Inject
    private ConfigService configService;

    @Inject
    private AuthService authService;

    private byte[] emailKEY = R.sg(24).next().getBytes();

    // 登陆页面
    @At("/login")
    @Filters()
    @Ok("beetl:/auth/login.html")
    public void login() {
    }

    // 登陆校验接口
    @At("/login")
    @POST
    @Filters()
    public Object login(User user, String captcha, HttpSession session) throws NoSuchAlgorithmException {

        NutMap re = new NutMap().setv("ok", false);
        if (!captcha.equalsIgnoreCase((String) session.getAttribute("captcha"))) {
            return re.setv("msg", "验证码错误！");
        }
        if (user == null || user.getEmail().equals("") || user.getPassword().equals("")) {
            return re.setv("msg", "邮箱或密码错误！");
        }

        // 验证邮箱是否存在
        if (userService.fetch(user.getEmail()) == null) {
            return re.setv("msg", "邮箱或密码错误！");
        }

        User originalUser = userService.fetch(user.getEmail());

        // 验证密码是否正确
        if (!MD5Util.Verify(user.getPassword(), originalUser.getPassword())) {
            return re.setv("msg", "邮箱或密码错误！");
        }
        session.setAttribute("user", originalUser.getEmail());
        if (originalUser.getPermissionId() == 1) {
            session.setAttribute("admin", originalUser.getPermissionId());
        }
        return re.setv("ok", true);
    }

    // 注册页面
    @At("/register")
    @Filters()
    @Ok("beetl:/auth/register.html")
    public void register() {
    }

    // 注册接口
    @At("/register")
    @POST
    @Filters()
    public Result register(User user, String repeatPassword, String captcha, HttpSession session) throws NoSuchAlgorithmException {
        if (!captcha.equalsIgnoreCase((String) session.getAttribute("captcha"))) {
            return ajaxError("验证码错误！");
        }
        if (user == null) {
            return ajaxError("用户不能为空！");
        }
        // 验证邮箱是否存在
        if (userService.fetch(user.getEmail()) != null) {
            return ajaxError("该邮箱已经被注册！");
        }
        // 验证密码是否正确
        if (!user.getPassword().equals(repeatPassword)) {
            return ajaxError("密码不一致！");
        }
        // 加密
        user.setPassword(MD5Util.Generate(user.getPassword()));

        // 增加用户
        userService.insert(user);
        return ajaxSuccess("用户注册成功！");
    }

    // 后台主页
    @At("")
    @Ok("beetl:/auth/index.html")
    public Object index(HttpServletRequest httpServletRequest) throws UnknownHostException {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userService.fetch((String) httpServletRequest.getSession().getAttribute("user")));
        map.put("config", configService.fetch(1));
        List<Article> articleList = articleService.query();
        map.put("articleList", articleList.size() > 5 ? articleList.subList(0, 5) : articleList.subList(0, articleList.size()));
        map.put("articleCount", articleList.size());
        int trafficVolume = 0;
        for (Article article : articleList) {
            trafficVolume += article.getBrowseVolume();
        }
        map.put("trafficVolume", trafficVolume);
        map.put("labelCount", labelService.count());

        List<Comment> commentList = commentService.query();
        map.put("commentList", commentList.size() > 5 ? commentList.subList(0, 5) : commentList.subList(0, commentList.size()));
        map.put("commentCount", commentList.size());
        map.put("friendCount", friendService.count());
        map.put("userCount", userService.count());
        map.put("serverPort", httpServletRequest.getServerPort());
        map.put("serverHost", InetAddress.getLocalHost().getHostAddress());
        return map;
    }

    // 退出登陆
    @At("/logout")
    @Ok(">>:/auth")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @At("/captcha")
    @Ok("raw:png")
    @Filters()
    public BufferedImage getCaptcha(HttpSession session) {

        String captcha = R.captchaChar(4);

        session.setAttribute("captcha", captcha);

        return Images.createCaptcha(captcha);
    }

    // 重置页面
    @At("/verify")
    @Filters()
    @Ok("beetl:/auth/verify.html")
    public void verify() {

    }


    // 重置页面
    @At("/reset")
    @GET
    @Filters()
    @Ok("beetl:/auth/reset.html")
    public Object reset(String token, HttpSession session) {
        NutMap re = new NutMap();
        User user = userService.fetch((String) DESUtil.Verify(token, emailKEY).get("email"));
        if (user != null) {
            session.setAttribute("token", token);
            return re.setv("ok", true);
        } else {
            return re.setv("msg", "非法token！");
        }
    }

    // 重置页面
    @At("/reset")
    @POST
    @Filters()
    public Object reset(String password, String repeatPassword, HttpSession session) throws NoSuchAlgorithmException {
        NutMap re = new NutMap();
        String token = (String) session.getAttribute("token");
        User user = userService.fetch((String) DESUtil.Verify(token, emailKEY).get("email"));
        if (user != null) {
            if (password == null || repeatPassword == null
                    || password.length() == 0 || repeatPassword.length() == 0) {
                return re.setv("msg", "密码错误！");
            } else if (password.equals(repeatPassword)) {
                password = MD5Util.Generate(password);
                user.setPassword(password);
                userService.update(user);
                session.invalidate();
                emailKEY = R.sg(24).next().getBytes();
                return re.setv("ok", true);
            }
        }
        return re.setv("msg", "验证失败！");
    }

    @At("/verify")
    @POST
    @Filters()
    public Object verify(String email, String captcha, HttpServletRequest httpServletRequest) {
        NutMap re = new NutMap();
        if (!captcha.equalsIgnoreCase((String) httpServletRequest.getSession().getAttribute("captcha"))) {
            return re.setv("msg", "验证码错误！");
        }
        if (userService.fetch(email) == null) {
            return re.setv("msg", "邮箱不存在！");
        }
        SimpleDateFormat yearMonthDay = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分ss秒");
        Calendar time = Calendar.getInstance();
        // token
        String token = String.format("%s,%s", email, System.currentTimeMillis());
        token = DESUtil._3DES_Encode(emailKEY, token.getBytes());
        // 设置邮件内容
        String html = "<div id=\"qm_con_body\">\n" +
                "    <div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\"> \n" +
                "        <div style=\"\">\n" +
                "            <div style=\" color:#666; font-size:14px; margin:0 auto; width:740px;\">\n" +
                "                <div style=\" float:left;  background:#fff no-repeat left top; border:1px solid #e3e3e3; padding:85px 70px 54px 70px; height:auto; width:600px; position:relative;\">\n" +
                "                    <div style=\" position:absolute; right:-52px; top:52px;\"></div>\n" +
                "                        <h1 style=\" color:#4882ce; font-size:22px;\">您好！</h1>\n" +
                "                    <div style=\" margin-top:35px;\">\n" +
                "                        <h1 style=\"color:#333; font-size:16px;\">您重置密码的验证链接为：<span style=\"border-bottom:1px dashed #ccc;z-index:1\" t=\"7\">" +
                "http://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + "/auth/reset?token=" + token +
                "</span></h1>\n" +
                "                        <p style=\"margin-top:25px;\">仅在五分钟内有效。</p>\n" +
                "                        <p style=\"margin-top:25px;\">PS.  如果这不是你本人的操作，请忽略此封邮件。</p>\n" +
                "                    </div>\n" +
                "                    <div style=\" border-top:1px solid #ebedf1; padding-top:45px; margin:30px 0 20px 0;\">南溟<a href=\"https://southseast.cc\" title=\"南溟\" target=\"_blank\" style=\" color:#4882ce; margin:0 10px; text-decoration:none;\">southseast.cc</a><p><span style=\"border-bottom:1px dashed #ccc;\" t=\"5\" times=\"\">" +
                yearMonthDay.format(time.getTime()) +
                "</span></p></div>\n" +
                "                </div>\t\t\n" +
                "                <div style=\" clear:both; color:#757575; font-size:10px; padding:20px 0 80px; text-align:center;\">\n" +
                "                    <p style=\"margin:0; padding:0;\">这是一封系统确认函，请不要回复此邮件！此邮件的地址无法接受您来信。</p>\n" +
                "                    <p style=\"margin:0; padding:0;\"> © 2019 <a href=\"https://southseast.cc\" target=\"_blank\" style=\"color:#4882ce; text-decoration:none;\">southseast.cc</a>. All Rights Reserved.</p>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <style type=\"text/css\">.qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}</style>\n" +
                "    </div>\n" +
                "</div>";


        try {
            boolean ok = authService.send(email, "密码重置", html);
            if (!ok) {
                return re.setv("msg", "发送失败！");
            }
        } catch (Throwable e) {
            return re.setv("msg", "发送失败！");
        }
        return re.setv("ok", true);
    }

}
