package com.tengyun.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * @author tengyun
 * @date 2021/2/3 19:54
 **/
public class Test {

    public static void main(String[] args) {
        //创建cglib获取代理对象的操作对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyCalculator.class);
        enhancer.setCallback(new MyCglib());

        MyCalculator myCalculator = (MyCalculator) enhancer.create();
        myCalculator.add(1,2);
        System.out.println(myCalculator.getClass());
    }

}
