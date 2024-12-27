<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-BookMan-登录</title>
    <style>
        /* 登录页面专属样式，限定在 .login-page 内 */
        .login-page {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f9f9f9;
            font-family: "Microsoft YaHei", Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .login-page .login-container {
            background-color: #ffffff;
            padding: 40px 50px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .login-page h1 {
            text-align: center;
            color: #333;
            font-size: 28px;
            margin-bottom: 30px;
        }

        .login-page .form-group {
            margin-bottom: 20px;
        }

        .login-page .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #555;
            font-size: 16px;
        }

        .login-page .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            box-sizing: border-box;
        }

        .login-page .form-group input:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .login-page .submit-btn {
            display: block;
            width: 100%;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 12px;
            border-radius: 8px;
            font-size: 18px;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .login-page .submit-btn:hover {
            background-color: #0056b3;
        }

        .login-page .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #666;
        }

        .login-page .footer a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .login-page .footer a:hover {
            text-decoration: underline;
        }

        .login-page .session-error-message {
            display: block;
            font-size: 18px;
            color: red;
            text-align: center;
        }
    </style>
</head>

<%
    String errorMessage = (String) request.getSession().getAttribute("errorMessage");
    request.getSession().setAttribute("errorMessage", "");
%>

<body>
<jsp:include page="../components/Navbar.jsp" />
<!-- 加入唯一的命名空间类 -->
<div class="login-page">
    <div class="login-container">
        <h1>用户登录</h1>
        <span id="session_error_message" class="session-error-message">
                <%= errorMessage == null ? "" : errorMessage %>
            </span>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <!-- 用户名 -->
            <div class="form-group">
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username" placeholder="请输入用户名" required>
            </div>

            <!-- 密码 -->
            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" id="password" name="password" placeholder="请输入密码" required>
            </div>

            <!-- 提交按钮 -->
            <button type="submit" class="submit-btn">登录</button>
        </form>

        <div class="footer">
            没有账号？<a href="${pageContext.request.contextPath}/register">立即注册</a>
        </div>
    </div>
</div>
<jsp:include page="../components/Footerbar.jsp" />
</body>
</html>


</html>
