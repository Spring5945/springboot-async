package com.zhengyu.eventsource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener implements ApplicationListener<UserEvent> {
    private static final Logger logger = LoggerFactory.getLogger("UserEventListener");

    @Override
    @Async("myCacheThreadPool")
    public void onApplicationEvent(UserEvent userEvent) {
        logger.info("------------ApplicationListener监听方式处理事件：{}", userEvent.getMsg());
        try {
            Thread.sleep(5 * 1000L);
            logger.info("ApplicationListener监听方式事件处理完成,新增用户一条");
        } catch (InterruptedException e) {
        }
    }
}
