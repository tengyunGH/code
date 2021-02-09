package com.tengyun.design.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射破坏单例模式
 * 可通过
 * @author tengyun
 * @date 2021/2/8 15:41
 **/
public class ReflexDestroySingleton {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 获取一个单例
        DoubleCheckSingleton doubleCheckSingleton = DoubleCheckSingleton.getInstance();
        // 通过反射获取一个
        Constructor<DoubleCheckSingleton> declaredConstructor = DoubleCheckSingleton.class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        DoubleCheckSingleton doubleCheckSingleton1 = declaredConstructor.newInstance();
        // 打印出false
        System.out.println(doubleCheckSingleton == doubleCheckSingleton1);
    }

}
