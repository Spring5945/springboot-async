package com.zhengyu.service;

import com.zhengyu.eventsource.EventPublisher;
import com.zhengyu.eventsource.UserEvent;
import com.zhengyu.infrastructure.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    EventPublisher eventPublisherl;
    Map<Long, User> userMap = new HashMap<>(1);
    private static final Logger logger = LoggerFactory.getLogger("UserServiceImpl");

    @Override
    @Async("myCacheThreadPool")
    public void saveUser(User user) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        userMap.put(user.getUserId(), user);
        logger.info("userService:saveUser:finish");
    }

    @Override
    public void saveUserAsync(User user) {
        eventPublisherl.publish(new UserEvent(this,"saveUserAsync"));
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
        logger.info("userService:getUserInfo:finish");
        return new AsyncResult<>(userMap.get(userId));
    }
}
