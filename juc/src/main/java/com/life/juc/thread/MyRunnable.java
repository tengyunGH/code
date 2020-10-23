package com.life.juc.thread;

/**
 * @author tengyun
 * @date 2020/10/20 19:41
 **/
public class MyRunnable implements Runnable {

    TestObject test;

    public MyRunnable(TestObject test) {
        this.test = test;
    }

    @Override
    public void run() {
        try {
            test.getName();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
