package com.zhengyu.eventsource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserEventHandler {
    private static final Logger logger = LoggerFactory.getLogger("UserEventHandler");

    @EventListener
    @Async("myCacheThreadPool")
    public void handleEvent(UserEvent event) {
        logger.info("------------EventListener监听方式处理事件：{}", event.getMsg());
        try {
            Thread.sleep(5 * 1000L);
            logger.info("EventListener监听方式事件处理完成,新增用户一条");
        } catch (InterruptedException e) {
        }
    }

}
