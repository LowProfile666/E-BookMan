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
import java.util.List;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
@WebServlet({"/update", "/search"})
public class UpdateServlet extends HttpServlet {
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("url", "update");
        if (req.getRequestURI().contains("search")) doSearch(req);
        req.getRequestDispatcher("views/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = Book.builder()
                .id(Integer.parseInt(request.getParameter("id")))
                .isbn(request.getParameter("isbn"))
                .publisher(request.getParameter("publisher"))
                .category(request.getParameter("category"))
                .name(request.getParameter("name"))
                .price(Double.parseDouble(request.getParameter("price")))
                .remarks(request.getParameter("remarks"))
                .build();
        boolean res = bookService.updateBook(book);
        request.setAttribute("updateResult", res);
        request.setAttribute("updatedBook", book);
        request.getRequestDispatcher("views/updateBook.jsp").forward(request, response);
    }

    private void doSearch(HttpServletRequest request) {
        String isbn = request.getParameter("isbn");
        if (isbn.isBlank()) {
            request.setAttribute("searchResult", false);
            return;
        }
        List<Book> books = bookService.searchBooks(isbn, "isbn");
        if (books.isEmpty()) {
            request.setAttribute("searchResult", false);
            return;
        }
        request.setAttribute("searchResult", true);
        request.setAttribute("updateBook", books.get(0));
    }
}
