package com.zhengyu.service;

import com.zhengyu.infrastructure.User;

import java.util.concurrent.Future;

public interface UserService {
    void saveUser(User user);

    void saveUserAsync(User user);

    User findOne(Long userId);

    Future<User> getUserInfo(Long userId);
}
