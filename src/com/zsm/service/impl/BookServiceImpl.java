package com.zsm.service.impl;

import com.zsm.bean.Book;
import com.zsm.mapper.BookMapper;
import com.zsm.mapper.impl.BookMapperImpl;
import com.zsm.service.BookService;

import java.util.List;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public class BookServiceImpl implements BookService {
    private final BookMapper bookMapper = new BookMapperImpl();

    @Override
    public List<Book> getBooks(Map<String, String> query) {
        return bookMapper.getBookList(query);
    }

    @Override
    public Book getBookById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("书籍 ID 必须为正整数");
        }
        return bookMapper.getBookById(id);
    }

    @Override
    public boolean addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("书籍信息不能为空");
        }
        if (book.getName() == null || book.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("书籍名称不能为空");
        }
        if (book.getPrice() <= 0) {
            throw new IllegalArgumentException("书籍价格必须大于 0");
        }
        List<Book> books = bookMapper.searchBooks(book.getIsbn(), "isbn");
        if (!books.isEmpty()) {
            return false;
        }
        return bookMapper.addBook(book);
    }

    @Override
    public boolean updateBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("书籍信息不能为空");
        }
        if (book.getId() <= 0) {
            throw new IllegalArgumentException("书籍 ID 必须为正整数");
        }
        if (book.getName() == null || book.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("书籍名称不能为空");
        }
        return bookMapper.updateBook(book);
    }

    @Override
    public boolean deleteBook(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("书籍 ID 必须为正整数");
        }
        return bookMapper.deleteBook(id);
    }

    @Override
    public List<Book> searchBooks(String keyword, String searchType) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("搜索关键字不能为空");
        }
        if (!"name".equals(searchType) && !"isbn".equals(searchType) && !"publisher".equals(searchType)) {
            throw new IllegalArgumentException("搜索类型必须为 'name'、'isbn' 或 'publisher'");
        }
        return bookMapper.searchBooks(keyword, searchType);
    }

    @Override
    public int count(Map<String, String> query) {
        return bookMapper.count(query);
    }
}
