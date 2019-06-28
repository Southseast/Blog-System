<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>南溟有猫</title>
    <%@ include file="./common/meta.jsp" %>
</head>
<body>
<div class="container sidebar-position-left page-archive">
    <%@ include file="./common/header.jsp" %>
    <main id="main" class="main">
        <div class="main-inner">
            <div class="content-wrap">
                <div id="content" class="content">
                    <div class="post-block archive" style="opacity: 1; display: block;">
                        <div id="posts" class="posts-collapse">
                            <span class="archive-move-on"></span>
                            <span class="archive-page-counter"> ${obj.label.labelName} 分类 </span>

                            <c:forEach var="articles" items="${obj.articleList}">

                                <c:forEach var="article" items="${articles}" varStatus="status">
                                    <c:if test="${status.index == 0}">
                                        <div class="collection-title"
                                             style="opacity: 1; display: block; transform: translateX(0px);">
                                            <h2 class="archive-year"
                                                style="opacity: 1; display: block; transform: translateX(0px);">${article.createY}</h2>
                                        </div>
                                    </c:if>
                                    <article class="post post-type-normal">
                                        <header class="post-header"
                                                style="opacity: 1; display: block; transform: translateY(0px);">
                                            <h3 class="post-title">
                                                <a class="post-title-link"
                                                   href="/article/${article.articleId}">
                                                    <span>${article.title}</span>
                                                </a>
                                            </h3>
                                            <div class="post-meta">
                                                <time class="post-time"> ${article.createMD}
                                                </time>
                                            </div>
                                        </header>
                                    </article>
                                </c:forEach>

                            </c:forEach>

                        </div>
                    </div>
                    <nav class="pagination" style="opacity: 1; display: block;">
                        <c:choose>
                            <%--总页数没有7页--%>
                            <c:when test="${obj.totalPages <= 7}">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="${obj.totalPages}"/>
                            </c:when>
                            <%--页数超过了7页--%>
                            <c:otherwise>
                                <c:set var="begin" value="${obj.page - 1}"/>
                                <c:set var="end" value="${obj.page + 3}"/>
                                <%--如果begin减1后为0,设置起始页为1,最大页为7--%>
                                <c:if test="${begin -1 <= 0}">
                                    <c:set var="begin" value="1"/>
                                    <c:set var="end" value="6"/>
                                </c:if>
                                <%--如果end超过最大页,设置起始页=最大页-5--%>
                                <c:if test="${end > obj.totalPages}">
                                    <c:set var="begin" value="${obj.page - 5}"/>
                                    <c:set var="end" value="${obj.page}"/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        <%--遍历--%>
                        <c:forEach var="i" begin="${begin}" end="${end}">
                            <%--当前页,选中--%>
                            <c:choose>
                                <c:when test="${i == obj.page}">
                                    <span class="page-number current">${obj.page}</span>
                                </c:when>
                                <%--不是当前页--%>
                                <c:otherwise>
                                    <a class="page-number" href="/label/${obj.label.labelId}/${i}/">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </nav>

                </div>
            </div>

            <%@ include file="./common/aside.jsp" %>
        </div>
    </main>

    <%@ include file="./common/footer.jsp" %>
</div>
</body>

</html>
