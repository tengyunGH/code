package com.tengyun.design.pattern.singleton;

/**
 * 懒汉模式
 * @author tengyun
 * @date 2021/2/8 15:28
 **/
public class LazySingleton {

    private LazySingleton() {}

    private static volatile LazySingleton lazySingleton = null;

    public static LazySingleton getInstance() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }
        return lazySingleton;
    }

    private Integer data;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }
}
