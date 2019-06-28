<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>用户管理</title>
    <%@ include file="../common/meta.jsp" %>
</head>
<body>
<!-- 顶部开始 -->
<%@ include file="../common/top.jsp" %>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<%@ include file="../common/left_nav.jsp" %>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">


    <div class="weadmin-body">
        <blockquote class="layui-elem-quote">用户管理</blockquote>
        <div class="layui-card">
            <div class="layui-card-body">
                <div class="layui-row">
                    <form class="layui-form layui-col-md12 we-search">
                        <div class="layui-inline">
                            <input type="text" name="email" autocomplete="off" placeholder="请输入邮箱"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="nickname" autocomplete="off" placeholder="请输入昵称"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <select name="sex">
                                <option value="">请选择性别</option>
                                <option value="1">男</option>
                                <option value="2">女</option>
                            </select>
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="telphone" autocomplete="off" placeholder="请输入电话"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="region" autocomplete="off" placeholder="请输入地区"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="occupation" autocomplete="off" placeholder="请输入工作"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="school" autocomplete="off" placeholder="请输入毕业院校"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="signature" autocomplete="off" placeholder="请输入个性签名"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="blogAddress" autocomplete="off" placeholder="请输入博客地址"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="githubAddress" autocomplete="off" placeholder="请输入Github地址"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" id="startTime" name="createTime" autocomplete="off"
                                   placeholder="开始时间"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" id="endTime" name="updateTime" autocomplete="off" placeholder="结束时间"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn" lay-submit lay-filter="search"><i
                                    class="layui-icon">&#xe615;</i>
                            </button>
                        </div>
                    </form>
                </div>
                <table class="layui-hide" id="manage" lay-filter="manage"></table>
                <script type="text/html" id="topbar">
                    <div class="layui-btn-container">
                        <button class="layui-btn layui-btn-danger" lay-event="delAll"><i class="layui-icon"></i>批量删除
                        </button>
                        <button class="layui-btn layui-btn-normal"
                                onclick="WeAdminShow('用户编辑', '/auth/user/edit/', 500, 850)"><i
                                class="layui-icon"></i>添加
                        </button>
                    </div>
                </script>
                <script type="text/html" id="toolbar">
                    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                    <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
                </script>
            </div>
        </div>

    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->

<%@ include file="../common/footer.jsp" %>
<!-- 底部结束 -->
<script type="text/javascript">
    layui.config({
        base: '/resources/static/js/'
    }).use('admin');

    layui.use(['table', 'jquery', 'form', 'laydate'], function () {
        var table = layui.table
            , form = layui.form
            , laydate = layui.laydate
            , $ = layui.$;

        laydate.render({
            elem: '#startTime' //指定元素
        });

        laydate.render({
            elem: '#endTime' //指定元素
        });

        var manage = table.render({
            elem: '#manage'
            , url: '/auth/user/list'
            , method: 'post'
            , toolbar: '#topbar'
            , cellMinWidth: 80
            , cols: [[
                {type: 'numbers'},
                {type: 'checkbox'},
                {field: 'email', title: '邮箱'},
                {field: 'nickname', title: '昵称'},
                {
                    field: 'sex', title: '性别'
                    , templet: function (data) {
                        if (data.sex == 1)
                            return "男";
                        else if (data.sex == 2)
                            return "女";
                        else
                            return "";
                    }
                },
                {field: 'telphone', title: '电话'},
                {field: 'region', title: '地区'},
                {field: 'occupation', title: '工作'},
                {field: 'school', title: '学校'},
                {field: 'signature', title: '个性签名'},
                {field: 'blogAddress', title: '博客地址'},
                {field: 'githubAddress', title: 'Github地址'},
                {fixed: 'right', align: 'center', toolbar: '#toolbar'}
            ]],
            page: true
        });

        table.on('toolbar(manage)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'delAll':
                    $.ajax({
                        url: '/auth/article/deleteList',
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify(checkStatus.data),
                        success: function (result) {
                            if (result.ok) {
                                layer.msg('删除成功', {icon: 6, time: 1000}, function () {
                                    manage.reload();
                                });
                            } else {
                                layer.msg(result.msg, {icon: 5, time: 2000});
                            }
                        }
                    });
                    break;
            }
        });

        table.on('tool(manage)', function (obj) {
            var data = obj.data;
            if (obj.event === 'delete') {
                $.ajax({
                    url: '/auth/user/delete',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        email: data.email
                    },
                    success: function (result) {
                        if (result.ok) {
                            layer.msg('删除成功', {icon: 6, time: 1000}, function () {
                                manage.reload();
                            });
                        } else {
                            layer.msg(result.msg, {icon: 5, time: 2000});
                        }
                    }
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2,
                    title: '用户编辑',
                    content: '/auth/user/edit/' + btoa(data.email),
                    area: ['500px', '850px']
                })
            }
        });

        //监听提交
        form.on('submit(search)', function (data) {
            manage.reload({
                where: data.field
            });
            return false;
        });
    });
</script>
</body>
</html>