<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="./common/meta.jsp" %>
    <title>南溟有猫</title>
</head>

<body>
<div class="container sidebar-position-left page-home">
    <%@ include file="./common/header.jsp" %>
    <main id="main" class="main">
        <div class="main-inner">
            <div class="content-wrap">
                <div id="content" class="content">
                    <section id="posts" class="posts-expand">
                        <c:forEach var="article" items="${obj.articleList}">
                            <article class="post post-type-normal">
                                <div class="post-block" style="opacity: 1; display: block;">
                                    <header class="post-header"
                                            style="opacity: 1; display: block; transform: translateY(0px);">
                                        <h2 class="post-title">
                                            <a class="post-title-link" href="/article/${article.articleId}">
                                                    ${article.title} </a>
                                        </h2>
                                        <div class="post-meta">
                                            <span class="post-time">
                                                <span class="post-meta-item-icon">
                                                    <i class="fa fa-calendar-o"></i>
                                                </span>
                                                <span class="post-meta-item-text">发表于</span>
                                                <span>${article.createTime}</span>
                                                <span class="post-meta-divider">|</span>
                                                <span class="post-meta-item-icon">
                                                    <i class="fa fa-calendar-check-o"></i>
                                                </span>
                                                <span class="post-meta-item-text">更新于</span>
                                                <span>${article.updateTime}</span>
                                            </span>
                                            <span class="post-comments-count">
                                                <span class="post-meta-divider">|</span>
                                                <span class="post-meta-item-icon">
                                                    <i class="fa fa-comment-o"></i>
                                                </span>
                                                <span class="post-meta-item-text">评论数：</span>
                                                <span>${article.commentList.size()}</span>
                                            </span>
                                            <span class="leancloud_visitors">
                                                <span class="post-meta-divider">|</span>
                                                <span class="post-meta-item-icon">
                                                    <i class="fa fa-eye"></i>
                                                </span>
                                                <span class="post-meta-item-text">热度：</span>
                                                <span class="leancloud-visitors-count">
                                                        ${article.browseVolume}</span>
                                                <span>℃</span>
                                            </span>
                                        </div>
                                    </header>

                                    <div class="post-body" itemprop="articleBody"
                                         style="opacity: 1; display: block; transform: translateY(0px);">
                                        <p>
                                        <div class="three—elements-div">${article.content}</div>
                                        </p>
                                        <!--noindex-->
                                        <div class="post-button text-center">
                                            <a class="btn" href="/article/${article.articleId}" rel="contents">阅读全文
                                                » </a>
                                        </div>
                                        <!--/noindex-->
                                    </div>
                                    <footer class="post-footer">
                                        <div class="post-eof"></div>
                                    </footer>
                                </div>
                            </article>
                        </c:forEach>

                    </section>

                    <c:if test="${obj.articleList.size() > 0}">

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
                                    <c:if test="${begin - 1 <= 0}">
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
                                        <a class="page-number" href="/${i}/">${i}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </nav>
                    </c:if>

                </div>
            </div>

            <%@ include file="./common/aside.jsp" %>

        </div>
    </main>

    <%@ include file="./common/footer.jsp" %>
</div>
</body>

</html>
