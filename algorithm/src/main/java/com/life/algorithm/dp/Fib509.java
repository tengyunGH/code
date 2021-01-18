package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/15 16:24
 **/
public class Fib509 {

    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int last = 0, cur = 1, temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = cur;
            cur = last + cur;
            last = temp;
        }
        return cur;
    }

}
