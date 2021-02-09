package com.life.juc.threadpool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/2/5 20:34
 **/
public class ScheduleThreadPoolTest {

    public static void main(String[] args) {

        ConcurrentHashMap<Integer, Object> map = new ConcurrentHashMap<>();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("wozhixingle +++++++++");
            }
        }, 3, 4, TimeUnit.SECONDS);

    }

}
