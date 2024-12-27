package com.zsm.mapper.impl;

import com.mysql.cj.util.StringUtils;
import com.zsm.bean.Book;
import com.zsm.mapper.BookMapper;
import com.zsm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public class BookMapperImpl implements BookMapper {
    @Override
    public List<Book> getBookList(Map<String, String> query) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 基础 SQL 查询语句
            StringBuilder sql = new StringBuilder("SELECT * FROM book WHERE 1=1");
            List<Object> params = new ArrayList<>();

            // 动态拼接查询条件
            String name = query.get("name");
            if (!StringUtils.isNullOrEmpty(name)) {
                sql.append(" AND name LIKE ?");
                params.add("%" + query.get("name") + "%");
            }
            String isbn = query.get("isbn");
            if (!StringUtils.isNullOrEmpty(isbn)) {
                sql.append(" AND isbn = ?");
                params.add(query.get("isbn"));
            }
            String category = query.get("category");
            if (!StringUtils.isNullOrEmpty(category)) {
                sql.append(" AND category = ?");
                params.add(query.get("category"));
            }
            String publisher = query.get("publisher");
            if (!StringUtils.isNullOrEmpty(publisher)) {
                sql.append(" AND publisher LIKE ?");
                params.add("%" + query.get("publisher") + "%");
            }

            // 处理分页参数
            String curPage = query.get("currentPage");
            int currentPage = StringUtils.isNullOrEmpty(curPage) ? 1 : Integer.parseInt(curPage);
            int pageSize = Integer.parseInt( query.get("pageSize"));
            int offset = (currentPage - 1) * pageSize;

            sql.append(" LIMIT ? OFFSET ?");
            params.add(pageSize);
            params.add(offset);

            // 获取连接并执行查询
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());

            // 设置 PreparedStatement 参数
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rs = ps.executeQuery();
            List<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt("id"))
                        .category(rs.getString("category"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .isbn(rs.getString("isbn"))
                        .publisher(rs.getString("publisher"))
                        .remarks(rs.getString("remarks"))
                        .build();
                list.add(book);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
    
    @Override
    public Book getBookById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM book WHERE id = ?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Book.builder()
                        .id(rs.getInt("id"))
                        .category(rs.getString("category"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .isbn(rs.getString("isbn"))
                        .publisher(rs.getString("publisher"))
                        .remarks(rs.getString("remarks"))
                        .createTime(rs.getString("create_time"))
                        .updateTime(rs.getString("update_time"))
                        .isDeleted(rs.getBoolean("is_deleted"))
                        .build();
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    @Override
    public boolean updateBook(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "UPDATE book SET category = ?, name = ?, price = ?, isbn = ?, publisher = ?, remarks = ? WHERE id = ?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getCategory());
            ps.setString(2, book.getName());
            ps.setDouble(3, book.getPrice());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublisher());
            ps.setString(6, book.getRemarks());
            ps.setInt(7, book.getId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // 更新行数大于 0 表示更新成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public boolean deleteBook(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "DELETE FROM book WHERE id = ?";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // 删除行数大于 0 表示删除成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public boolean addBook(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO book (category, name, price, isbn, publisher, remarks) VALUES (?, ?, ?, ?, ?, ?)";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getCategory());
            ps.setString(2, book.getName());
            ps.setDouble(3, book.getPrice());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublisher());
            ps.setString(6, book.getRemarks());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // 插入成功
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public List<Book> searchBooks(String keyword, String searchType) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql;
            switch (searchType) {
                case "name":
                    sql = "SELECT * FROM book WHERE name LIKE ?";
                    break;
                case "isbn":
                    sql = "SELECT * FROM book WHERE isbn LIKE ?";
                    break;
                case "publisher":
                    sql = "SELECT * FROM book WHERE publisher LIKE ?";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid search type: " + searchType);
            }

            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();

            List<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book book = Book.builder()
                        .id(rs.getInt("id"))
                        .category(rs.getString("category"))
                        .name(rs.getString("name"))
                        .price(rs.getDouble("price"))
                        .isbn(rs.getString("isbn"))
                        .publisher(rs.getString("publisher"))
                        .remarks(rs.getString("remarks"))
                        .build();
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }

    @Override
    public int count(Map<String, String> query) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 基础 SQL 查询语句
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM book WHERE 1=1");
            List<Object> params = new ArrayList<>();

            // 动态拼接查询条件
            String name = query.get("name");
            if (!StringUtils.isNullOrEmpty(name)) {
                sql.append(" AND name LIKE ?");
                params.add("%" + name + "%");
            }
            String isbn = query.get("isbn");
            if (!StringUtils.isNullOrEmpty(isbn)) {
                sql.append(" AND isbn = ?");
                params.add(isbn);
            }
            String category = query.get("category");
            if (!StringUtils.isNullOrEmpty(category)) {
                sql.append(" AND category = ?");
                params.add(category);
            }
            String publisher = query.get("publisher");
            if (!StringUtils.isNullOrEmpty(publisher)) {
                sql.append(" AND publisher LIKE ?");
                params.add("%" + publisher + "%");
            }

            // 获取连接并执行查询
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());

            // 设置 PreparedStatement 参数
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rs = ps.executeQuery();

            // 获取查询结果
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // 返回 -1 表示查询出错
        } finally {
            DBUtil.close(conn, ps, rs); // 关闭资源
        }

        return 0; // 返回 0 表示没有匹配记录
    }

}
