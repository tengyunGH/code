package com.tengyun.redis.question;

import com.google.common.hash.Hashing;
import redis.clients.jedis.Jedis;

/**
 * redis实现布隆过滤器
 * https://www.jianshu.com/p/744a89307c2d
 * @author tengyun
 * @date 2021/2/19 19:46
 **/
public class BloomFilterRedis {
    static final int expectedInsertions = 1000;//要插入多少数据
    static final double fpp = 0.01;//期望的误判率

    //bit数组长度
    private static long numBits;

    //hash函数数量
    private static int numHashFunctions;

    static {
        numBits = optimalNumOfBits(expectedInsertions, fpp);
        numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        for (int i = 0; i < 1000; i++) {
            long[] indexArray = getIndexArray(String.valueOf(i));
            for (long index : indexArray) {
                jedis.setbit("codebear:bloom", index, true);
            }
        }
        int num = 0;
        for (int i = 1000; i < 2000; i++) {
            long[] indexArray = getIndexArray(String.valueOf(i));
            for (long index : indexArray) {
                if (!jedis.getbit("codebear:bloom", index)) {
                    System.out.println(i + "一定不存在");
                    num++;
                    break;
                }
            }
        }
        System.out.println("一定不存在的有" + num + "个");
    }

    /**
     * 根据key获取bitmap下标
     */
    private static long[] getIndexArray(String key) {
        long hash1 = hash(key);
        long hash2 = hash1 >>> 16;
        long[] result = new long[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            long combinedHash = hash1 + i * hash2;
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            result[i] = combinedHash % numBits;
        }
        return result;
    }

    private static long hash(String key) {
        return key.hashCode();
    }

    //计算hash函数个数
    private static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / n * Math.log(2)));
    }

    //计算bit数组长度
    private static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }
}
