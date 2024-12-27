package com.zsm.mapper;

import com.zsm.bean.R;
import com.zsm.bean.User;
import com.zsm.util.DBUtil;

import java.sql.Connection;
import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public interface UserMapper {
    R addUser(User user);

    User selectUserByUsernamePassword(String username, String password);

}
