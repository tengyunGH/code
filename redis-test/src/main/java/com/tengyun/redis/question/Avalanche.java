package com.tengyun.redis.question;

/**
 * 缓存雪崩
 * 同一时间大量的key同时失效，导致大量请求打到DB上
 * 1、设置key的缓存时间的时候，可以增加一个随机数的时间值，这样就降低了缓存时间重复的概率
 * 2、用加锁或者队列的方式来保证缓存的单线程写。
 * @author tengyun
 * @date 2021/2/4 8:59
 **/
public class Avalanche {



}
