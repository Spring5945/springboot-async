package com.zhengyu.controller;

import com.zhengyu.infrastructure.User;
import com.zhengyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/user")
public class MyRestController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/query/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId) {
        return userService.findOne(userId);
    }

    @RequestMapping(value = "/save/{userId}", method = RequestMethod.GET)
    public User saveUser(@PathVariable Long userId) {
        User edison = User.UserBuilder.anUser().withUserId(userId).withName("edison").withAge(25).build();
        userService.saveUser(edison);
        System.out.println("controller:saveUser:finish");
        return edison;
    }

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public User getUserInfo(@PathVariable Long userId) {
        Future<User> userFuture = userService.getUserInfo(userId);
        User user = null;
        try {
            user = userFuture.get();
            System.out.println("controller:getUserInfo:finish");
        } catch (InterruptedException e) {
            System.out.println("controller:getUserInfo:InterruptedException " + e);
        } catch (ExecutionException e) {
            System.out.println("controller:getUserInfo:ExecutionException " + e);
        }
        return user;
    }
}