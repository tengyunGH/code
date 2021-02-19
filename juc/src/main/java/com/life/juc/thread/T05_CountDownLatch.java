package com.life.juc.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author tengyun
 * @date 2021/2/18 19:13
 **/
public class T05_CountDownLatch {

    volatile List<Integer> lists = new ArrayList<>();

    public void add(Integer i) {
        lists.add(i);
    }

    public int size() {
        return lists.size();
    }

    public static void main1(String[] args) {
        T05_CountDownLatch t05_countDownLatch = new T05_CountDownLatch();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        new Thread(() -> {
            System.out.println("t2启动了");
           if (t05_countDownLatch.size() != 3) {
               try {
                   latch1.await();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
            latch2.countDown();
            System.out.println("t2结束了");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动了");
            for (int i = 0; i < 6; i++) {
                t05_countDownLatch.add(i);
                System.out.println("add " + i);
                if (t05_countDownLatch.size() == 3) {
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1结束了");
        }).start();

    }

    public static void main(String[] args) {
        T05_CountDownLatch t05_countDownLatch = new T05_CountDownLatch();

        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动了");
                if (t05_countDownLatch.size() != 3) {
                    try {
                        // 阻塞，会让出锁
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
                System.out.println("t2结束了");
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized(lock) {
                System.out.println("t1启动了");
                for (int i = 0; i < 6; i++) {
                    t05_countDownLatch.add(i);
                    System.out.println("add " + i);
                    if (t05_countDownLatch.size() == 3) {
                        // 唤醒使用lock.wait()的线程，但是不会让出锁
                        lock.notify();
                        try {
                            // 阻塞会让出锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("t1结束了");
            }
        }).start();

    }


    public static void main2(String[] args) {
        T05_CountDownLatch t05_countDownLatch = new T05_CountDownLatch();
        Thread t2 = new Thread(() -> {
            System.out.println("t2启动了");
            if (t05_countDownLatch.size() != 3) {
                LockSupport.park();
            }
            System.out.println("t2结束了");
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t1 = new Thread(() -> {
            System.out.println("t1启动了");
            for (int i = 0; i < 6; i++) {
                t05_countDownLatch.add(i);
                System.out.println("add " + i);
                if (t05_countDownLatch.size() == 3) {
                    LockSupport.unpark(t2);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("t1结束了");
        });
        t1.start();


    }



}
