package com.zsm.controller.book;

import com.zsm.bean.Book;
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
 * Time :  2024/12/27
 */
@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Book book = bookService.getBookById(Integer.parseInt(id));
        request.setAttribute("detailBook", book);
        request.getRequestDispatcher("views/detail.jsp").forward(request, response);
    }
}
