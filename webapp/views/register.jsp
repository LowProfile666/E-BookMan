<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-BookMan-注册</title>
    <style>
        /* 通用样式 */
        .page-container {
            font-family: "Microsoft YaHei", Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 80vh;
            margin: 0;
        }

        .page-container .register-container {
            background-color: #ffffff;
            padding: 10px 50px;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            width: 500px;
            max-width: 90%;
            max-height: 90%;
            overflow-y: auto;
            margin: 80px auto 20px auto;
        }

        /* 其他样式也加上 `page-container` 限定 */
        .page-container h1 {
            text-align: center;
            color: #333;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .page-container .form-group {
            margin-bottom: 10px;
        }

        .page-container .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
            color: #555;
            font-size: 14px;
        }

        .page-container .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            box-sizing: border-box;
        }

        .page-container .form-group input:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .page-container .submit-btn {
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

        .page-container .submit-btn:hover {
            background-color: #0056b3;
        }

        .page-container .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #666;
        }

        .page-container .footer a {
            text-decoration: none;
            color: #007bff;
            font-weight: bold;
        }

        .page-container .footer a:hover {
            text-decoration: underline;
        }

        .page-container .error-message {
            display: none;
            color: #ff4d4f;
            font-size: 14px;
        }

        .page-container .session-error-message {
            display: block;
            font-size: 18px;
            color: red;
            text-align: center;
        }



    </style>
</head>
<body>

<%
    String errorMessage = (String) request.getSession().getAttribute("errorMessage");
    request.getSession().setAttribute("errorMessage", "");
%>
<jsp:include page="../components/Navbar.jsp"/>

<div class="page-container">
    <div class="register-container">
        <h1>用户注册</h1>
        <span id="session_error_message"
              class="session-error-message"><%=errorMessage == null ? "" : errorMessage%></span>
        <form action="${pageContext.request.contextPath}/register" method="post" id="registerForm">
            <!-- 用户名 -->
            <div class="form-group">
                <label for="username">用户名：<span class="error-message" id="usernameError"></span></label>
                <input type="text" id="username" name="username" placeholder="请输入用户名" required>

            </div>

            <!-- 密码 -->
            <div class="form-group">
                <label for="password">密码：<span class="error-message" id="passwordError"></span></label>
                <input type="password" id="password" name="password" placeholder="请输入密码" required>

            </div>

            <!-- 确认密码 -->
            <div class="form-group">
                <label for="confirmPassword">确认密码：<span class="error-message"
                                                            id="confirmPasswordError"></span></label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="请再次输入密码"
                       required>

            </div>

            <!-- 邮箱 -->
            <div class="form-group">
                <label for="email">邮箱：<span class="error-message" id="emailError"></span></label>
                <input type="email" id="email" name="email" placeholder="请输入邮箱地址" required>

            </div>

            <!-- 手机号 -->
            <div class="form-group">
                <label for="phone">手机号：<span class="error-message" id="phoneError"></span></label>
                <input type="text" id="phone" name="phone" placeholder="请输入手机号" required>
            </div>

            <!-- 提交按钮 -->
            <button type="submit" class="submit-btn">注册</button>
        </form>


        <div class="footer">
            已有账号？<a href="${pageContext.request.contextPath}/login">立即登录</a>
        </div>
    </div>
</div>

<jsp:include page="../components/Footerbar.jsp" />
</body>
</html>
<script>
    document.getElementById("registerForm").addEventListener("submit", function (event) {
        let isValid = true;

        // 获取表单数据
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;

        // 清空之前的错误信息
        document.querySelectorAll(".error-message").forEach(function (error) {
            error.style.display = "none";
        });
        document.querySelector("#session_error_message").innerText = "";

        // 验证确认密码
        if (password !== confirmPassword) {
            document.getElementById("confirmPasswordError").innerText = "两次密码输入不一致！";
            document.getElementById("confirmPasswordError").style.display = "inline-block";
            isValid = false;
        }

        // 如果验证未通过，阻止表单提交
        if (!isValid) {
            event.preventDefault();
        }
    });

</script>
