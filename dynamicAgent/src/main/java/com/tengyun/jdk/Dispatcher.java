package com.tengyun.jdk;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tengyun
 * @date 2021/2/3 16:57
 **/
public class Dispatcher {

    private static final Dispatcher DISPATCHER;

    static {
        DISPATCHER = new Dispatcher();
    }

    private Dispatcher() {

    }

    public static Dispatcher getDispatcher() {
        return DISPATCHER;
    }

    private static final ConcurrentHashMap<String, Object> DIS = new ConcurrentHashMap<>();

    public static void register(String key, Object object) {
        DIS.put(key, object);
    }

    public static Object get(String className) {
        return DIS.get(className);
    }

}
