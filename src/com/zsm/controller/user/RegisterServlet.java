package com.zsm.controller.user;

import com.zsm.bean.R;
import com.zsm.bean.User;
import com.zsm.service.UserService;
import com.zsm.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("url", "register");
        req.getRequestDispatcher("views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取表单数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        // 验证密码是否一致
        if (!password.equals(confirmPassword)) {
            request.getSession().setAttribute("errorMessage", "两次输入的密码不一致！");
            response.sendRedirect("register");
            return;
        }

        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phone(phone)
                .build();
        R res = userService.addUser(user);
        if (res.getCode() == 200)
            response.sendRedirect("login");
        else {
            request.getSession().setAttribute("errorMessage", res.getMsg());
            response.sendRedirect("register");
        }
    }
}
