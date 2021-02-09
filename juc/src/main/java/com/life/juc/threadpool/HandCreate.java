package com.life.juc.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/2/5 16:13
 **/
public class HandCreate {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(20), new MyThreadFactory());

    }

}
