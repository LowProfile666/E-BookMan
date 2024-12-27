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
 * Time :  2024/12/26
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("url", "add");
        req.getRequestDispatcher("views/addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = Book.builder()
                .isbn(request.getParameter("isbn"))
                .publisher(request.getParameter("publisher"))
                .category(request.getParameter("category"))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .remarks(request.getParameter("remarks"))
                .build();
        boolean res = bookService.addBook(book);
        request.setAttribute("addResult", res);
        if (!res)
            book = bookService.searchBooks(book.getIsbn(), "isbn").get(0);
        request.setAttribute("book", book);
        request.getRequestDispatcher("views/addBook.jsp").forward(request, response);
    }
}
