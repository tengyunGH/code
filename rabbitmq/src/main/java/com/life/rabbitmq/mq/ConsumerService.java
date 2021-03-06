package com.life.rabbitmq.mq;

import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author tengyun
 * @date 2021/3/2 15:10
 **/
@Service
public class ConsumerService {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(200));

    public void execute(Object msg) {
        Runnable runnable = () -> System.out.println(msg);
        executor.submit(runnable);
    }
}
