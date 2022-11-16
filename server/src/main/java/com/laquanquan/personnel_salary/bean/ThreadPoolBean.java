package com.laquanquan.personnel_salary.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lqq
 */
@Configuration
public class ThreadPoolBean {

    @Bean(name = "EmailExecutor")
    public ExecutorService get() {
        // 创建线程池
        return new ThreadPoolExecutor(5, 10, 5
                , TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
            private final AtomicInteger atomicInteger = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                // 自定义命名
                return new Thread(r, "Email-Handler-" + atomicInteger.getAndIncrement());
            }
        });
    }
}
