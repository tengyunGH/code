package com.tengyun.jdk;

/**
 * @author tengyun
 * @date 2021/2/3 9:38
 **/
public class MyCalculator1 implements Calculator {
    @Override
    public int add(int a, int b) {
        System.out.println("MyCalculator1");
        return a+b;
    }

}
