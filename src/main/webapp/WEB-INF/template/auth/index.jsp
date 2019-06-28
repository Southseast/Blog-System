<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
    <title>概况</title>
    <%@ include file="./common/meta.jsp" %>
</head>
<body>
<!-- 顶部开始 -->
<%@ include file="./common/top.jsp" %>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<%@ include file="./common/left_nav.jsp" %>

<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="weadmin-body">

        <blockquote class="layui-elem-quote">概况&nbsp;|&nbsp;你好！${obj.user.nickname}，现在是&nbsp;<div id="gettime"
                                                                                                  style="display: inline-block;"></div>
        </blockquote>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">

                <div class="layui-col-md12 layui-col-space15">
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card">
                            <div class="layui-card-header">
                                访问量
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">
                                <p class="layuiadmin-big-font">${obj.trafficVolume}</p>
                                <p>总计访问量</p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card" onclick="window.open('/auth/article','_self')">
                            <div class="layui-card-header">
                                文章数
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">
                                <p class="layuiadmin-big-font">${obj.articleCount}</p>
                                <p>总计文章数</p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card" onclick="window.open('/auth/comment','_self')">
                            <div class="layui-card-header">
                                评论数
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">
                                <p class="layuiadmin-big-font">${obj.commentCount}</p>
                                <p>总计评论数</p>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm6 layui-col-md3">
                        <div class="layui-card" onclick="window.open('/auth/user','_self')">
                            <div class="layui-card-header">
                                用户量
                            </div>
                            <div class="layui-card-body layuiadmin-card-list">
                                <p class="layuiadmin-big-font">${obj.userCount}</p>
                                <p>总计用户量</p>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="layui-col-md12 layui-col-space15">
                    <div class="layui-col-sm4">
                        <div class="layui-card">
                            <div class="layui-card-header">用户留言</div>
                            <div class="layui-card-body">
                                <ul class="layuiadmin-card-status layuiadmin-home2-usernote">
                                    <c:forEach var="comment" items="${obj.commentList}">
                                        <li>
                                            <a href="/article/${comment.articleId}" style="color: #666666">
                                                <h3>${comment.user.nickname}</h3></a>有新的留言，点击查看。<br/>
                                                <%--                                            <div class="three—elements-div">${comment.content}</div>--%>
                                            <span>${comment.createTime}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-sm4">
                        <div class="layui-card">
                            <div class="layui-card-header">最近发布的文章</div>
                            <div class="layui-card-body">
                                <ul class="layuiadmin-card-status layuiadmin-home2-usernote">
                                    <c:forEach var="article" items="${obj.articleList}">
                                        <li>
                                            <a href="/article/${article.articleId}" style="color: #666666">
                                                <h3>${article.title}</h3></a>
                                            热度:${article.browseVolume}<br/>
                                                <%--                                            <div class="three—elements-div-auth">${article.content}</div>--%>
                                            <span>${article.createTime}</span>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md4">

                        <div class="layui-card">
                            <div class="layui-card-header">博客信息</div>
                            <div class="layui-card-body">
                                <table class="layui-table">
                                    <tbody>
                                    <tr>
                                        <th width="40%">博客名称</th>
                                        <td><span id="lbServerName">${obj.config.blogName}</span></td>
                                    </tr>
                                    <tr>
                                        <td>好友总数</td>
                                        <td>${obj.friendCount}</td>
                                    </tr>
                                    <tr>
                                        <td>创建时间</td>
                                        <td>${obj.config.createTime}</td>
                                    </tr>
                                    <tr>
                                        <td>服务器地址</td>
                                        <td>${obj.serverHost}</td>
                                    </tr>
                                    <tr>
                                        <td>端口</td>
                                        <td>${obj.serverPort}</td>
                                    </tr>
                                    <tr>
                                        <td>作者</td>
                                        <td>南溟</td>
                                    </tr>
                                    <tr>
                                        <td>基于框架</td>
                                        <td>
                                            Layui&nbsp;2.4.5&nbsp;|&nbsp;Nutz&nbsp;1.r.68&nbsp;|&nbsp;jQuery&nbsp;3.4.1
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>


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
<%@ include file="./common/footer.jsp" %>
<!-- 底部结束 -->
<script type="text/javascript">
    function time() {
        var vWeek, vWeek_s, vDay;
        vWeek = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
        var date = new Date();
        year = date.getFullYear();
        month = date.getMonth() + 1;
        day = date.getDate();
        hours = date.getHours();
        minutes = date.getMinutes();
        seconds = date.getSeconds();
        vWeek_s = date.getDay();
        document.getElementById("gettime").innerHTML = year + "年" + month + "月" + day + "日" + "\t" + hours + ":" + minutes + ":" + seconds + "\t" + vWeek[vWeek_s];

    };
    setInterval("time()", 1000);
    window.onload = gettime;
    layui.config({
        base: '${base}/resources/static/js/'
    }).use('admin');
    layui.use(['jquery', 'element'], function () {
        var element = layui.element,
            $ = layui.jquery;
    });

</script>
</body>
</html>