<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="/auth/">
                    <i class="iconfont"></i>
                    <cite>概况</cite>
                    <i class="iconfont nav_right"></i>
                </a>
            </li>
            <c:if test="${obj.user.permissionId == 1}">
                <li>
                    <a href="/auth/article">
                        <i class="iconfont"></i>
                        <cite>文章</cite>
                        <i class="iconfont nav_right"></i>
                    </a>
                </li>
                <li>
                    <a href="/auth/label">
                        <i class="iconfont"></i>
                        <cite>分类</cite>
                        <i class="iconfont nav_right"></i>
                    </a>
                </li>
            </c:if>
            <li>
                <a href="/auth/comment">
                    <i class="iconfont"></i>
                    <cite>评论</cite>
                    <i class="iconfont nav_right"></i>
                </a>
            </li>
            <c:if test="${obj.user.permissionId == 1}">
                <li>
                    <a href="/auth/friend">
                        <i class="iconfont"></i>
                        <cite>好友</cite>
                        <i class="iconfont nav_right"></i>
                    </a>
                </li>
                <li>
                    <a href="/auth/user">
                        <i class="iconfont"></i>
                        <cite>用户</cite>
                        <i class="iconfont nav_right"></i>
                    </a>
                </li>
                <li>
                    <a href="/auth/config">
                        <i class="iconfont"></i>
                        <cite>设置</cite>
                        <i class="iconfont nav_right"></i>
                    </a>
                </li>
            </c:if>
        </ul>
    </div>
</div>