package com.life.application.classloader;

/**
 * @author tengyun
 * @date 2020/9/2 17:41
 **/
public class Hello {

    public String tengyun = "tengyun";

    public static Integer yu = 10;

    final int c;

    static int i = 9;
    final static int j = 7;

    static {
        System.out.println("static   " + yu);
    }

    public Hello(int c) {
        System.out.println("initial   " + yu);
        this.c = c;
    }

    public void test() {
        System.out.println("+++++++++++++test");
    }
}
