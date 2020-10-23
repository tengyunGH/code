package com.life.application.basicjava.fiber;

import co.paralleluniverse.fibers.Fiber;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author tengyun
 * @date 2020/9/10 14:33
 **/
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class FiberTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//                threadUsed();
        // fiberUsed();
        fiberAnThreadUsed();
    }


    public static void threadUsed() throws InterruptedException {
        long time1 = System.currentTimeMillis();
        int size = 1000;
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(FiberTest::cal);
        }
        System.out.println("创建线程耗时 " + (System.currentTimeMillis() - time1));
        for (int i = 0; i < size; i++) {
            threads[i].start();
        }
        System.out.println("使用线程耗时 " + (System.currentTimeMillis() - time1));
    }

    public static void fiberUsed() throws ExecutionException, InterruptedException {
        long time1 = System.currentTimeMillis();
        int size = 100;
        Fiber<Void>[] fibers = new Fiber[size];
        for (int i = 0; i < fibers.length; i++) {
            fibers[i] = new Fiber<Void>(FiberTest::cal);
        }
        System.out.println("创建纤程耗时 " + (System.currentTimeMillis() - time1));
        for (int i = 0; i < size; i++) {
            fibers[i].start();
        }
        System.out.println("使用纤程耗时 " + (System.currentTimeMillis() - time1));
    }

    public static void fiberAnThreadUsed() throws InterruptedException {
        long time1 = System.currentTimeMillis();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fiberUsed();
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
        System.out.println("使用线程纤程耗时 " + (System.currentTimeMillis() - time1));
        container.remove(0);
    }
    private static List<Node> container = new ArrayList<>();

    class Node {
        private int previous;

        private int next;

        private int content;

        public int getPrevious() {
            return previous;
        }

        public void setPrevious(int previous) {
            this.previous = previous;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        public int getContent() {
            return content;
        }

        public void setContent(int content) {
            this.content = content;
        }
    }

    public static int cal() {
        int i = 10000, count = 0;
        while (i > 0) {
            count = +i;
            i--;
        }
        return count;
    }

}
