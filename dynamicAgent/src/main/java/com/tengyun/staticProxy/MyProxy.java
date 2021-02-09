package com.tengyun.staticProxy;

/**
 * @author tengyun
 * @date 2021/2/3 20:15
 **/
public class MyProxy implements Calculator {

    public MyProxy(MyCalculator myCalculator) {
        this.myCalculator = myCalculator;
    }

    private final MyCalculator myCalculator;

    @Override
    public int add(int a, int b) {
        System.out.println("+++++++after");
        int add = myCalculator.add(a, b);
        System.out.println("+++++++before");
        return add;
    }

}
