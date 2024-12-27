<%@ page import="com.zsm.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css"/>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
<%
    User user = (User) session.getAttribute("user");
%>
<style>
    .logo-img {
        width: 38px;
        height: 38px;
    }

    .mynavbar {
        position: fixed !important;
        z-index: 9;
        top: 0;
        right: 0;
        left: 0;
    }
</style>

<%
    String url = (String) request.getSession().getAttribute("url");
%>

<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark border-body mynavbar" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">
            <img src="/static/images/java.svg" alt="E-BookMan" class="logo-img">
            E-BookMan</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link <%="index".equals(url) ? "active" : ""%>" aria-current="page" href="${pageContext.request.contextPath}/index">主页</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%="add".equals(url) ? "active" : ""%>" href="${pageContext.request.contextPath}/add">添加</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%="update".equals(url) ? "active" : ""%>" href="${pageContext.request.contextPath}/update">修改</a>
                </li>
            </ul>
            <span class="navbar-text">
                <%
                    if (user == null) {
                %>
                    <a href="${pageContext.request.contextPath}/login" type="button" class="btn btn-primary">登录</a>
                    <a href="${pageContext.request.contextPath}/register" type="button"
                       class="btn btn-secondary">注册</a>
                <%
                } else {
                %>
                    <div>
                        <span style="font-size: large; ">${user.getUsername()}</span>
                        <a type="button" class="btn" style="color: lightblue" href="${pageContext.request.contextPath}/logout">退出登录</a>
                    </div>
                <%
                    }
                %>
            </span>
        </div>
    </div>
</nav>