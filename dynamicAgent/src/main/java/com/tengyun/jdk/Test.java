package com.tengyun.jdk;

/**
 * @author tengyun
 * @date 2021/2/2 20:22
 **/
public class Test {

    public static void main(String[] args) {
        Dispatcher.register(MyCalculator.class.getName(), new MyCalculator());
        Dispatcher.register(MyCalculator1.class.getName(), new MyCalculator1());

        Calculator proxy = (Calculator) CalculatorProxy.getProxy(MyCalculator1.class.getName());
        System.out.println(proxy.add(1,1));
        System.out.println(proxy.getClass());

        Calculator proxy1 = (Calculator) CalculatorProxy.getProxy(MyCalculator.class.getName());
        System.out.println(proxy1.add(1,1));
        System.out.println(proxy1.getClass());
    }


}
