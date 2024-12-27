package com.zsm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Author : ZSM
 * Time :  2024/12/27
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String uri = request.getRequestURI();

        if (uri.contains("login") || uri.contains("logout") || uri.contains("register") || uri.contains("index")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (user == null) {
            request.getRequestDispatcher("/login").forward(request, servletResponse);
            return;
        }

        filterChain.doFilter(request, servletResponse);
    }
}
