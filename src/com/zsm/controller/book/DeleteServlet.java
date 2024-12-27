package com.zsm.controller.book;

import com.zsm.service.BookService;
import com.zsm.service.impl.BookServiceImpl;
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
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        boolean res = bookService.deleteBook(Integer.parseInt(id));
        request.setAttribute("res", res);
        request.getRequestDispatcher("/index").forward(request, response);
    }
}
