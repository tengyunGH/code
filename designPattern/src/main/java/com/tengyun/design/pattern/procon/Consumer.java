package com.tengyun.design.pattern.procon;

/**
 * @author tengyun
 * @date 2021/3/1 16:29
 **/
public class Consumer extends Thread {

    private CacheQueue<Integer> cacheQueue;

    public Consumer(CacheQueue<Integer> cacheQueue) {
        this.cacheQueue = cacheQueue;
    }

    @Override
    public void run() {
        for (;;) {
            Integer integer = cacheQueue.get();
            System.out.println(integer);
        }
    }
}
