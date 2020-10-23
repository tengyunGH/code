package com.life.juc.thread;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author tengyun
 * @date 2020/10/20 19:42
 **/
public class Test {


    public static void main(String[] args) throws InterruptedException {
        TestObject test = new TestObject("tengyun");
        System.out.println("main (((((( " + ClassLayout.parseInstance(test).toPrintable());
        MyRunnable myRunnable = new MyRunnable(test);
        Thread a = new Thread(myRunnable, "线程a");
        a.start();
        Thread.sleep(1);
        Thread b = new Thread(myRunnable, "线程b");
        b.start();
    }

}
