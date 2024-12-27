package com.zsm.service;

import com.zsm.bean.Book;

import java.util.List;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public interface BookService {
    List<Book> getBooks(Map<String, String> query);

    Book getBookById(int id);

    boolean addBook(Book book);

    boolean updateBook(Book book);

    boolean deleteBook(int id);

    List<Book> searchBooks(String keyword, String searchType);

    int count(Map<String, String> query);
}
