<%@ page contentType="text/html; charset=UTF-8" %>
<div class="container">
    <div class="logo">
        <a href="/">${obj.config.blogName}</a>
    </div>
    <div class="left_open">
        <!-- <i title="展开左侧栏" class="iconfont">&#xe699;</i> -->
        <i title="展开左侧栏" class="layui-icon layui-icon-shrink-right"></i>
    </div>
    <ul class="layui-nav right" style="color:#666666;">
        <li class="layui-nav-item">
            <a href="javascript:">${obj.user.nickname}</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd>
                    <a onclick="WeAdminShow('个人信息','/auth/user/edit/' + window.btoa('${obj.user.email}'), 500, 850)"
                       style="color:#666666;">个人信息</a>
                </dd>
                <dd>
                    <a class="loginout" href="/auth/logout" style="color:#666666;">退出</a>
                </dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index">
            <a href="/">返回博客</a>
        </li>
    </ul>
</div>