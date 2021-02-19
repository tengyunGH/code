package com.life.springdemo.utils;


import com.google.gson.Gson;

/**
 * @author tengyun
 * @date 2021/2/19 10:35
 **/
public class JsonUtil {

    public static String javaBeanToString(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object stringToJavaBean(String string, Class c) {
        Gson gson = new Gson();
        return gson.fromJson(string, c);
    }

}
