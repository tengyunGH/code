package com.life.juc.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tengyun
 * @date 2021/2/5 14:54
 **/
public class ProductionTest {


    public static void test(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
            60, TimeUnit.SECONDS, new SynchronousQueue<>(),
            new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        BusinessService service = new BusinessServiceImpl();
        Future<Integer> submit = threadPoolExecutor.submit(service::handlerService);
        try {
            Integer integer = submit.get();
            System.out.println(integer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        SynchronousQueue<Integer> integers = new SynchronousQueue<>(true);


    }

    public static void main(String[] args) throws InterruptedException {

        int cpuNum = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuNum);

        BusinessService service = new BusinessServiceImpl();
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(cpuNum,
            cpuNum+2, 60, TimeUnit.SECONDS,
            new SynchronousQueue<>(true),
            new MyThreadFactory(),  new ThreadPoolExecutor.DiscardOldestPolicy());
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            tasks.add(service::handlerService);
        }
        try {
//            Future<Integer> submit = threadPoolExecutor.submit(tasks.get(0));
//            Integer integer1 = submit.get();
//            System.out.println("integer1 result is "+integer1);
            List<Future<Integer>> futures = threadPoolExecutor.invokeAll(tasks);
            // 任务都执行完了再来执行下面的
            System.out.println("++++++++++++++++++++++"+Thread.currentThread().getName());
            futures.forEach((future) -> {
                Integer integer = null;
                try {
                    integer = future.get();
                } catch (Exception e) {
                    System.out.println("出现异常");
                    e.printStackTrace();
                }
                System.out.println(integer);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("__________________________________");
        threadPoolExecutor.shutdown();
    }

}
