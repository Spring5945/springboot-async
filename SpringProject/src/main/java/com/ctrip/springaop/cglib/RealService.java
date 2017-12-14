package com.ctrip.springaop.cglib;

/**
 * 真实主题,测试cglib代理用
 * Created by zhengyu.nie on 2017/12/13.
 */
public class RealService {

    public void sayHello() {
        System.out.println("helloworld");
    }
}
