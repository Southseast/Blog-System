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
        <input name="password" placeholder="密码" type="password" lay-verify="required" class="layui-input login-input">
        <hr class="hr15">
        <input name="repeatPassword" placeholder="重复密码" type="password" lay-verify="required"
               class="layui-input login-input">
        <hr class="hr15">
        <input value="重置密码" type="submit" lay-submit lay-filter="reset" style="width:100%;" class="loginin">
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
        //监听提交
        form.on('submit(reset)', function (data) {
            $.ajax({
                url: '/auth/reset',
                type: 'POST',
                dataType: 'json',
                data: data.field,
                success: function (result) {
                    if (result.ok) {
                        layer.msg("重置成功", {icon: 6, time: 1000}, function () {
                            window.location.href = '/auth/login';
                        });
                    } else {
                        layer.msg(result.msg, {icon: 5});
                    }
                }
            });
            return false;
        });
    });

</script>
<!-- 底部结束 -->
</body>
</html>