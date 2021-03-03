package com.tengyun.design.pattern.procon;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者按照顺序一个一个的放，当map的大小等于size的时候，阻塞生产者
 * 消费者判断，如果map的大小等于0，就阻塞消费者；
 * 消费者就根据map的Iterator获取下一个，并删除
 * @author tengyun
 * @date 2021/3/1 16:36
 **/
public class CacheQueue<T> {

    public CacheQueue(Integer size, LinkedHashMap<Integer, T> queue) {
        this.currentIndex = 0;
        this.size = size;
        this.queue = queue;
        lock = new ReentrantLock();
        putCondition = lock.newCondition();
        getCondition = lock.newCondition();
    }
    /**
     * 当前商品的下标
     **/
    private int currentIndex;
    /**
     * 队列最大的大小
     **/
    private Integer size;

    /**
     * 队列
     **/
    private LinkedHashMap<Integer, T> queue;

    private Lock lock;

    private Condition putCondition;

    private Condition getCondition;

    /**
     * 生产者向队列中添加东西
     * @author tengyun
     * @date 16:38 2021/3/1
     * @param product 添加的东西
     **/
    public void put(T product) {
        try {
            lock.lock();
            // 队列满了，生产者阻塞
            if (queue.size() == size) {
                putCondition.await();
            }
            queue.put(currentIndex, product);
            currentIndex = currentIndex+1 > size ? currentIndex+1 % size : currentIndex+1;
            // 唤醒消费者
            getCondition.signalAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
            System.out.println("释放锁");
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            // 没有东西了，阻塞消费者
            if (queue.size() == 0) {
                getCondition.await();
            }
            Iterator<Map.Entry<Integer, T>> iterator = queue.entrySet().iterator();
            if (iterator.hasNext()) {
                Map.Entry<Integer, T> next = iterator.next();
                t = next.getValue();
                int key = next.getKey();
                queue.remove(key);
            }
            putCondition.signalAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
            System.out.println("释放锁");
        }
        return t;
    }

}
