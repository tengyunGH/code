package com.tengyun.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author tengyun
 * @date 2021/2/2 20:16
 **/
public class CalculatorProxy {

    public static Object getProxy(String className) {
        Object o = Dispatcher.get(className);
        Class<?> aClass = o.getClass();
        return Proxy.newProxyInstance(aClass.getClassLoader(),
            aClass.getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println(method.getName());
                    Object invoke = method.invoke(o, args);
                    System.out.println("lallllal after");
                    return invoke;
                }
            });
    }

}
