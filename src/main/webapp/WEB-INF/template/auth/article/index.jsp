<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>文章管理</title>
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
        <blockquote class="layui-elem-quote">文章管理</blockquote>
        <div class="layui-card">
            <div class="layui-card-body">
                <div class="layui-row">
                    <form class="layui-form layui-col-md12 we-search">
                        <div class="layui-inline">
                            <input type="text" name="title" autocomplete="off" placeholder="请输入标题"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <input type="text" name="content" autocomplete="off" placeholder="请输入内容"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline">
                            <select name="labelId">
                                <option value="">请选择分类</option>
                                <c:forEach var="label" items="${obj.labelList}">
                                    <option value="${label.labelId}">${label.labelName}</option>
                                </c:forEach>
                            </select>
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
                                onclick="WeAdminShow('文章编辑', '/auth/article/edit', 1000, 900)"><i
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
            , url: '/auth/article/list'
            , method: 'post'
            , cellMinWidth: 80
            , toolbar: '#topbar'
            , cols: [[
                {type: 'numbers'},
                {type: 'checkbox'},
                {field: 'title', title: '标题', width: 320},
                {field: 'content', title: '内容'},
                {
                    field: 'label', title: '分类'
                    , templet: function (data) {
                        if (data.labelId == 0)
                            return "";
                        else
                            return data.label.labelName;
                    }, width: 120
                },
                {field: 'browseVolume', title: '浏览量', width: 80, align: 'center'},
                {field: 'createTime', title: '发布日期', width: 120},
                {fixed: 'right', align: 'center', toolbar: '#toolbar', width: 200}
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
                    url: '/auth/article/delete',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        articleId: data.articleId
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
                    title: '文章编辑',
                    content: '/auth/article/edit/' + data.articleId,
                    area: ['1000px', '900px']
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