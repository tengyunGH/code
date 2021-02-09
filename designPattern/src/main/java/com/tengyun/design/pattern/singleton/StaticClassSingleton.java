package com.tengyun.design.pattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * 嵌套类单例模式
 * @author tengyun
 * @date 2021/2/8 15:34
 **/
public class StaticClassSingleton implements Serializable {

    private StaticClassSingleton(){
        System.out.println("我被建造了");
    }

    /**
     * 嵌套类
     * @author tengyun
     * @date 16:22 2021/2/8
     **/
    private static class Holder {
        private static final StaticClassSingleton STATIC_CLASS_SINGLETON = new StaticClassSingleton();
    }

    public static StaticClassSingleton getInstance() {
        return  Holder.STATIC_CLASS_SINGLETON;
    }

    private Integer data = 10;

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public static void main(String[] args) {
        StaticClassSingleton instance = StaticClassSingleton.getInstance();
    }

    /**
     * 有了这个方法，那么在反序列化的时候，就会执行这个方法
     * @author tengyun
     * @date 15:33 2021/2/9
     **/
    public Object readResolve() throws ObjectStreamException {
        return getInstance();
    }

}
