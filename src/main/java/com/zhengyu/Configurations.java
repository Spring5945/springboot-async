package com.zhengyu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

@Configuration
public class Configurations {
    LongAdder longAdder = new LongAdder();

    @Bean("myCacheThreadPool")
    public ExecutorService threadPool() {
        return Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            longAdder.add(1);
            thread.setName("myCacheThread-" + longAdder.sum());
            System.out.println("myCacheThread start");
            return thread;
        });
    }
}
