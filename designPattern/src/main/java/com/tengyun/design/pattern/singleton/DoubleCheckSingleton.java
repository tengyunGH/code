package com.tengyun.design.pattern.singleton;

import java.io.Serializable;

/*
 * 自己的私有静态对象
 * 私有的构造方法
 * 公有的获取单例的方法
 **/

/**
 * 双检查单例模式
 * @author tengyun
 * @date 2021/2/8 15:28
 **/
public class DoubleCheckSingleton implements Serializable {

    private DoubleCheckSingleton(){}

    private static volatile DoubleCheckSingleton singleton = null;

    public static DoubleCheckSingleton getInstance() {
        if (singleton == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new DoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }

    private Integer data = 10;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public static void main(String[] args) {
        Integer data = DoubleCheckSingleton.getInstance().getData();
        System.out.println(data);
    }

}
