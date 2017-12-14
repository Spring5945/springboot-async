package com.ctrip.springaop.security;

import com.ctrip.springaop.service.AuthService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    AuthService authService;

    //    @Pointcut("@annotation(AdminOnly)")
    @Pointcut("within(com.ctrip.springaop.service.sub.*)")
    public void service() {
        System.out.println("service execute");
    }

    @Before("service()")
    public void before() {

        System.out.println("before method execute");

    }

    @After("service()")
    public void after() {

        System.out.println("after method execute");

    }


}
