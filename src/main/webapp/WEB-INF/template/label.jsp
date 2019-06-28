<%@ page import="java.util.Random" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>南溟有猫</title>
    <%@ include file="./common/meta.jsp" %>
</head>
<body>
<div class="container sidebar-position-left page-post-detail">
    <%@ include file="./common/header.jsp" %>
    <main id="main" class="main">
        <div class="main-inner">
            <div class="content-wrap">
                <div id="content" class="content">
                    <div id="posts" class="posts-expand">
                        <div class="post-block page" style="opacity: 1; display: block;">
                            <header class="post-header" style="opacity: 1; display: block; transform: translateY(0px);">
                                <h2 class="post-title" itemprop="name headline">分类</h2>
                                <div class="post-meta"></div>
                            </header>
                            <div class="post-body" style="opacity: 1; display: block; transform: translateY(0px);">
                                <div class="tag-cloud">
                                    <div class="tag-cloud-title"> 目前共计 ${obj.labelCount} 个分类</div>
                                    <div class="tag-cloud-tags">
                                        <%
                                            Random a = new Random();
                                            String[] color = {"#818181", "#A7A7A7", "#5C5C5C", "#111111", "#CCCCCC"};
                                        %>
                                        <c:forEach var="label" items="${obj.labelList}">
                                            <a href="/label/${label.labelId}"
                                               style="font-size: <%=a.nextDouble()*20 + 10 %>px; color: <%=color[a.nextInt(5)]%>">${label.labelName}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="./common/aside.jsp" %>
        </div>
    </main>
    <%@ include file="./common/footer.jsp" %>
</div>
</body>
</html>
