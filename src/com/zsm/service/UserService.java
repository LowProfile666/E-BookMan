package com.zsm.service;

import com.zsm.bean.R;
import com.zsm.bean.User;

import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public interface UserService {
    R addUser(User user);
    R login(String username, String password);
}
