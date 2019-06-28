<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>撰写</title>
    <%@ include file="../common/meta.jsp" %>
</head>
<style>
    .simditor .simditor-body {
        /*开启滚动条*/
        overflow-y: scroll !important;
        /*设置高度*/
        min-height: 600px !important;
        max-height: 600px !important;
    }
</style>
<body>

<div class="layui-fluid" style="margin: 20px;">
    <form class="layui-form" action="">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <input name="commentId" type="hidden" value="${obj.commentId}">
                <input name="articleId" type="hidden" value="${obj.articleId}">
                <div class="layui-form-item layui-form-text">
                    <textarea id="content" name="content" placeholder="请输入内容" class="simditor"
                              style="resize:none; display: none;">${obj.content}</textarea>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit lay-filter="post" style="width: 100%">发表评论</button>
                </div>
            </div>


        </div>
    </form>
</div>

<%--<link rel="stylesheet" href="/resources/simditor/styles/app.css">--%>
<link rel="stylesheet" href="/resources/simditor/styles/mobile.css">
<link rel="stylesheet" href="/resources/simditor/styles/simditor.css">
<script src="/resources/simditor/scripts/module.js"></script>
<script src="/resources/simditor/scripts/uploader.js"></script>
<script src="/resources/simditor/scripts/hotkeys.js"></script>
<script src="/resources/simditor/scripts/simditor.js"></script>

<script type="text/javascript">


    var editor = new Simditor({
        toolbar: [
            'title', 'bold', 'italic', 'underline', 'strikethrough', 'fontScale',
            'color', '|', 'ol', 'ul', 'blockquote', 'code', 'table', '|', 'link',
            'image', 'hr', '|', 'alignment'
        ],
        textarea: '#content',
        placeholder: '写点什么...',
        defaultImage: '/resources/static/images/avatar.png',
        imageButton: ['upload'],
        upload: {
            url: '/auth/article/upload',
            params: null,
            fileKey: 'file',
            leaveConfirm: '正在上传文件..',
            connectionCount: 3
        },
        success: function (data) {
            alert(data);
        }
    });

    layui.config({
        base: '/resources/static/js/'
    }).use('admin');
    layui.use(['form', 'laydate'], function () {
        var laydate = layui.laydate
            , form = layui.form;
        laydate.render({
            elem: '#dataPickup' //指定元素
        });

        //监听提交
        form.on('submit(post)', function (data) {
            let index = parent.layer.getFrameIndex(window.name);
            $.ajax({
                url: '/auth/comment/edit',
                type: 'POST',
                dataType: 'json',
                data: data.field,
                success: function (result) {
                    if (result.ok) {
                        layer.msg("发表成功", {icon: 6, time: 1000}, function () {
                            parent.layui.table.reload('manage');
                            parent.layer.close(index);
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