package com.zsm.service.impl;

import com.zsm.bean.R;
import com.zsm.bean.User;
import com.zsm.mapper.UserMapper;
import com.zsm.mapper.impl.UserMapperImpl;
import com.zsm.service.UserService;

import java.util.Map;

/**
 * Author : ZSM
 * Time :  2024/12/26
 */
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper = new UserMapperImpl();

    @Override
    public R addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public R login(String username, String password) {
        User user = userMapper.selectUserByUsernamePassword(username, password);
        if (user != null)
            return R.OK("登录成功", user);
        return R.ERROR("登录失败");
    }
}
