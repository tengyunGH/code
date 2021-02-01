package com.life.juc.thread;

import java.io.File;

/**
 * @author tengyun
 * @date 2021/1/29 12:11
 **/
public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        Thread.sleep(2000);
        myRunnable.flag = false;
        Thread.sleep(2000);
        System.out.println(myRunnable.flag);
    }

    static class MyRunnable implements Runnable {
        // volatile字段使得当flag有更新的时候，其他线程的内存中这个字段就失效了，需要从主存中
        // boolean flag = true;
        volatile boolean flag = true;

        @Override
        public void run() {
            while (flag) {
                try {
                    /* 下面这三种
                     * 1、当CPU有空闲了会去主存中重新读取一下共享变量来更新线程工作内存中的值
                     * 2、打印输出是一个synchronized方法，释放锁的操作会导致该线程重新去读取共享变量的值
                     * 3、当操作引起了系统调用时，会有线程切换，那么这个线程原本的工作内存中的数值就会失效。打印和new File都会引起系统调用。
                     */
                    Thread.sleep(1);
                    System.out.println("1");
                    File file = new File("");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("run 结束");
        }
    }
}

