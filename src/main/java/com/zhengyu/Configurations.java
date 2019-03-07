package com.zhengyu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

@Configuration
public class Configurations {
    LongAdder longAdder = new LongAdder();
    private static final Logger logger = LoggerFactory.getLogger("Configurations");

    @Bean("myCacheThreadPool")
    public ExecutorService threadPool() {
        return Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            longAdder.add(1);
            thread.setName("myCacheThread-" + longAdder.sum());
            logger.info("myCacheThreadPool new thread:" + thread.getName());
            return thread;
        });
    }
}
