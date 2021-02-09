package com.life.juc.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/2/5 14:06
 **/
public class Test {

    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(1,1,1, TimeUnit.DAYS, new ArrayBlockingQueue<>(1)) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                super.beforeExecute(t, r);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
            }

            @Override
            protected void terminated() {
                super.terminated();
            }
        };


    }

}
