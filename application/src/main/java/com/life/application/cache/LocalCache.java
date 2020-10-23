package com.life.application.cache;

import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

/**

 * @author tengyun
 * @date 2020/8/28 20:07
 **/
public class LocalCache<T> {

    private ConcurrentHashMap<String, SoftReference<T>> localCache = new ConcurrentHashMap<>();

    public T get(String key) {
        SoftReference<T> softReference = localCache.get(key);
        if (softReference != null) {
            return softReference.get();
        }
        return null;
    }

    public void put(String key, T t, Long expireSeconds) {
        localCache.put(key, new SoftReference<T>(t));
    }

}
