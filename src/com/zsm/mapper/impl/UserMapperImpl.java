package com.zsm.mapper.impl;

import com.zsm.bean.R;
import com.zsm.bean.User;
import com.zsm.mapper.UserMapper;
import com.zsm.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public class UserMapperImpl implements UserMapper {
    @Override
    public R addUser(User user) {

        try(Connection conn = DBUtil.getConnection()) {
            String sql1 = "select username from user where username = ?";
            String sql2 = "insert into user values(null, ?, ?, ?, ?, null, null);";

            PreparedStatement ps = conn.prepareStatement(sql1);
            ps.setString(1, user.getUsername());
            ResultSet res = ps.executeQuery();
            
            if (res.next()) {
                return R.ERROR("用户已存在");
            }
            
            ps = conn.prepareStatement(sql2);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            
            if (ps.executeUpdate() == 1) {
                return R.OK();
            }

            return R.ERROR();
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
            return R.ERROR(e.getMessage());
        }
    }

    @Override
    public User selectUserByUsernamePassword(String username, String password) {
        try(Connection conn = DBUtil.getConnection()) {
            String sql = "select * from user where username = ? and password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet res = ps.executeQuery();

            User user = null;
            if (res.next()) {
                user = User.builder()
                        .username(res.getString("username"))
                        .password(res.getString("password"))
                        .email(res.getString("email"))
                        .phone(res.getString("phone"))
                        .id(res.getInt("id"))
                        .build();
            }

            return user;
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
            return null;
        }
    }
}
