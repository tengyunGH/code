package com.tengyun.design.pattern.procon;

/**
 * @author tengyun
 * @date 2021/3/1 16:29
 **/
public class Producer extends Thread {

    public Producer(CacheQueue<Integer> cacheQueue) {
        this.cacheQueue = cacheQueue;
    }

    private CacheQueue<Integer> cacheQueue;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            cacheQueue.put(i);
        }
    }

}
