package com.zsm.controller.book;

import com.zsm.service.BookService;
import com.zsm.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {
    public final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage") == null ? "1" : request.getParameter("currentPage");
        String publisher = request.getParameter("publisher");
        String category = request.getParameter("category");
        String name = request.getParameter("name");
        String isbn = request.getParameter("isbn");

        Map<String, String> query = new HashMap<String, String>();
        query.put("currentPage", currentPage);
        query.put("pageSize", "10");
        query.put("name", name);
        query.put("isbn", isbn);
        query.put("category", category);
        query.put("publisher", publisher);

        HttpSession session = request.getSession();
        session.setAttribute("count", bookService.count(query));
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("publisher", publisher);
        session.setAttribute("category", category);
        session.setAttribute("name", name);
        session.setAttribute("isbn", isbn);
        session.setAttribute("bookList", bookService.getBooks(query));

        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
