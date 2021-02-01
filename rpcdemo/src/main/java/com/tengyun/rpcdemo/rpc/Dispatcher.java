package com.tengyun.rpcdemo.rpc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tengyun
 * @date 2021/1/28 19:33
 **/
public class Dispatcher {

    private static Dispatcher dis = null;

    static {
        dis = new Dispatcher();
    }

    private Dispatcher() {

    }

    public static Dispatcher getDis() {
        return dis;
    }

    public  static ConcurrentHashMap<String,Object> invokeMap = new ConcurrentHashMap<>();

    public void register(String k,Object obj){
        invokeMap.put(k,obj);
    }
    public Object get(String k){
        return invokeMap.get(k);
    }
}
