package com.zhengyu.service;

import com.zhengyu.infrastructure.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class UserServiceImpl implements UserService {
    Map<Long, User> userMap = new HashMap<>(1);

    @Override
    @Async("myCacheThreadPool")
    public void saveUser(User user) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        int i = 10 / 0;
        System.out.println(i);
        userMap.put(user.getUserId(), user);
        System.out.println("userService:saveUser:finish");
    }

    @Override
    public User findOne(Long userId) {
        return userMap.get(userId);
    }

    @Override
    @Async("myCacheThreadPool")
    public Future<User> getUserInfo(Long userId) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        int i = 10 / 0;
        System.out.println(i);
        System.out.println("userService:getUserInfo:finish");
        return new AsyncResult<>(userMap.get(userId));
    }
}
