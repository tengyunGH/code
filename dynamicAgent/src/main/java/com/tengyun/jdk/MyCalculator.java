package com.tengyun.jdk;

/**
 * @author tengyun
 * @date 2021/2/2 20:15
 **/
public class MyCalculator implements Calculator {
    @Override
    public int add(int a, int b) {
        System.out.println("MyCalculator");
        return a+b;
    }

}
