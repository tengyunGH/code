package com.tengyun.redis.question;

import com.google.common.annotations.Beta;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 缓存穿透
 * 大量访问一个不存在的key，一般不为null我们才会缓存下来，这样的话，大量的请求就会穿过
 * 布隆过滤器
 * @author tengyun
 * @date 2021/2/4 9:06
 **/
public class PierceThrough {

    // 预计要插入的数据量
    private static int size = 1000000;

    // 期望的误判率
    private static double fpp = 0.001;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    public static void main(String[] args) {
        // redis中比如说是根据主键查的，那么就把主键放进去
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        int count = 0;

        // 这一步是检查肯定不存在的数据是否存在，看下误判率
        for (int i = 2000000; i < 3000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
            }
        }

        System.out.println(count);
    }

}
