package com.life.juc.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/2/5 16:59
 **/
public class TheadPoolType {

    public static void main(String[] args) {

        /*
         * 1、Singled
         * 1,1,0,millSeconds
         * defaultThreadFactory
         * LinkedBlockingQueue 基于链表实现的阻塞队列
         * AbortPolicy
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newSingleThreadExecutor(new MyThreadFactory());



        /*
         * 1、Fixed
         * 3,3,0,millSeconds
         * defaultThreadFactory
         * LinkedBlockingQueue 基于链表实现的阻塞队列
         * AbortPolicy
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
        ExecutorService executorService4 = Executors.newFixedThreadPool(3, new MyThreadFactory());


        /*
         * 1、Cached
         * 0, Integer.MAX_VALUE, 60, seconds
         * defaultFactory
         * SynchronousQueue
         * AbortPolicy
         */
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        ExecutorService executorService5 = Executors.newCachedThreadPool(new MyThreadFactory());

        /*
         * 1、Scheduled
         * 5, Integer.MAX_VALUE, 0, NANOSECONDS(纳秒)
         * defaultFactory
         * DelayedWorkQueue
         * AbortPolicy
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(5, new MyThreadFactory());


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
            60, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024),
            new MyThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

        ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<Runnable>(1024);
        LinkedBlockingQueue<Runnable> runnables1 = new LinkedBlockingQueue<>();
        SynchronousQueue<Runnable> runnables2 = new SynchronousQueue<>();



    }

}
