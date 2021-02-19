package com.tengyun.redis.question;

import com.tengyun.redis.verifytest.common.RedisSupport;

/**
 * 缓存击穿
 * 某一个热值key失效，导致大量请求打到DB
 * 1、使用互斥锁
 * 2、
 * 3、设置永不过期
 * @author tengyun
 * @date 2021/2/4 9:06
 **/
public class BreakDown {



    public static void main(String[] args) throws InterruptedException {

        RedisSupport redisSupport = new RedisSupport();
        String key = "tengyun";
        String v = redisSupport.get(key);
        String key_mutex = "ty";
/*        if (v == null) {
            if (redisSupport.set(key_mutex, 3 * 60 * 1000) == true) {
                value = db.get(key);
                redisSupport.set(key, value);
                redisSupport.delete(key_mutex);
            } else {
                Thread.sleep(50);
                retry();
            }
        } else {
            if (v.timeout <= now()) {
                if (redisSupport.add(key_mutex, 3 * 60 * 1000) == true) {
                    // extend the timeout for other threads
                    v.timeout += 3 * 60 * 1000;
                    redisSupport.set(key, v, KEY_TIMEOUT * 2);

                    // load the latest value from db
                    v = db.get(key);
                    v.timeout = KEY_TIMEOUT;
                    redisSupport.set(key, value, KEY_TIMEOUT * 2);
                    redisSupport.delete(key_mutex);
                } else {
                    Thread.sleep(50);
                    retry();
                }
            }
        }*/


    }

}
