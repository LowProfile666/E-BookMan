package com.zsm.mapper;

import com.zsm.bean.Book;

import java.util.List;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public interface BookMapper {
    List<Book> getBookList(Map<String, String> query);

    Book getBookById(int id);

    boolean updateBook(Book book);

    boolean deleteBook(int id);

    boolean addBook(Book book);

    List<Book> searchBooks(String keyword, String searchType);

    int count(Map<String, String> query);
}
