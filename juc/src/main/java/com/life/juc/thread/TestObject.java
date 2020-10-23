package com.life.juc.thread;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author tengyun
 * @date 2020/10/20 19:41
 **/
public class TestObject {

    public TestObject (String name) {
        this.name = name;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public  String getName() throws InterruptedException {
        System.out.println(Thread.currentThread() + "jinru++++++++++++++++++++++++++++++++++");
        synchronized (this) {
            System.out.println(Thread.currentThread() + ClassLayout.parseInstance(this).toPrintable());
            return name;
        }
    }

}
