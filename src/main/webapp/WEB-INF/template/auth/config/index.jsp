<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>基础管理</title>
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
        <blockquote class="layui-elem-quote">基础管理</blockquote>
        <div class="layui-fluid">
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body" style="padding: 15px;">
                        <form class="layui-form" action="">
                            <div class="layui-form-item">
                                <label class="layui-form-label">站点头像</label>
                                <div class="layui-input-block">
                                    <img class="author-image" itemprop="image" src="${obj.config.avatarUrl}" id="avatar"
                                         name="avatar">
                                    <button type="button" class="layui-btn" id="upload" name="upload">
                                        <i class="layui-icon">&#xe67c;</i>上传头像
                                    </button>
                                    <input type="hidden" name="avatarUrl" id="avatarUrl"
                                           value="${obj.config.avatarUrl}">
                                    <p id="hint"></p>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">站点名称</label>
                                <div class="layui-input-block">
                                    <input type="text" name="blogName" required lay-verify="required"
                                           placeholder="请输入站点名称"
                                           autocomplete="off" class="layui-input" value="${obj.config.blogName}">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit lay-filter="post">立即提交</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
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

    layui.use(['jquery', 'upload', 'form'], function () {
        var upload = layui.upload
            , form = layui.form
            , $ = layui.$;

        var uploadInst = upload.render({

            elem: '#upload'
            , url: '/upload'
            , before: function (obj) {
                //预读本地文件
                obj.preview(function (index, file, result) {
                    $('#avatar').attr('src', result);
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }
                document.getElementById('avatarUrl').value = res.data.src;
                //上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                var hint = $('#hint');
                hint.html('<span style="color: #FF5722; display: inline-block;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                hint.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        form.on('submit(post)', function (data) {
            $.ajax({
                url: '/auth/config/edit',
                type: 'POST',
                dataType: 'json',
                data: data.field,
                success: function (result) {
                    if (result.ok) {
                        layer.msg("更改成功", {icon: 6, time: 1000}, function () {
                            setTimeout('window.location.reload()', 1000);
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
</body>
</html>