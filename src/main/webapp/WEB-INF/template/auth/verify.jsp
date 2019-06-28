<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>重置密码</title>
    <%@ include file="./common/meta.jsp" %>
</head>
<body class="login-bg">
<div class="login">
    <div class="message">重置密码</div>
    <div id="darkbannerwrap"></div>
    <form method="post" class="layui-form">
        <input name="email" placeholder="邮箱" type="text" lay-verify="required" class="layui-input login-input">
        <hr class="hr15">
        <input name="captcha" placeholder="验证码" type="text" lay-verify="required" class="layui-input login-input"
               id="captcha"
               style="width: 49.4%; display: inline-block;vertical-align: middle">
        <img src="/auth/captcha" onclick="changeCaptcha();" id="captchaImg" class="login-input"
             style="width: 49.4%;height: 50px; border-radius: 3px;display: inline-block;vertical-align: middle;"
             alt="验证码刷新中">
        <hr class="hr15">
        <input value="发送邮件" type="submit" lay-submit lay-filter="verify" style="width:100%;" class="loginin">
        <hr class="hr20">
        <div>
            <a href="/" style="color: white">返回主页</a>
            <a href="/auth/login" style="float: right; color: white">返回登录</a>
        </div>
    </form>
</div>
<script type="text/javascript">
    layui.config({
        base: '/resources/static/js/'
    }).use('admin');
    layui.use(['form', 'admin'], function () {
        var form = layui.form
            , admin = layui.admin;

        form.on('submit(verify)', function (data) {
            $.ajax({
                url: '/auth/verify',
                type: 'POST',
                dataType: 'json',
                data: data.field,
                success: function (result) {
                    if (result.ok) {
                        layer.msg("发送成功", {icon: 6, time: 1000}, function () {
                            window.location.href = '/auth/login';
                        });
                    } else {
                        layer.msg(result.msg, {icon: 5});
                        changeCaptcha();
                    }
                }
            });
            return false;
        });
    });

    function changeCaptcha() {
        document.getElementById("captchaImg").src = "/auth/captcha?" + Math.random();
        document.getElementById("captcha").value = "";
    }

</script>
<!-- 底部结束 -->
</body>
</html>