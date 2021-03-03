package com.life.application.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tengyun
 * @date 2021/3/1 21:52
 **/
public class DemoOne {

    private String name;
    public int age;

    public DemoOne(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public DemoOne() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.life.application.reflect.DemoOne");

        Method setName = aClass.getMethod("setName", String.class);

        Constructor<?> constructor = aClass.getConstructor();

        Object o = constructor.newInstance();

        setName.invoke(o, "tengyun");

        Method getName = aClass.getMethod("getName");

        System.out.println(getName.invoke(o));

    }

}
