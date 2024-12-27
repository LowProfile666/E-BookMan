package com.zsm.controller.user;

import com.zsm.bean.R;
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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("url", "login");
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        R res = userService.login(username, password);
        if (res.getCode() != 200) {
            request.getSession().setAttribute("errorMessage", res.getMsg());
            response.sendRedirect("login");
            return;
        }

        request.getSession().setAttribute("user", res.getData());
        response.sendRedirect("index");
    }
}
