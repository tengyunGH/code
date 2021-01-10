package com.life.algorithm.dp;

import java.util.Arrays;

/**
 * @author tengyun
 * @date 2021/1/10 14:38
 **/
public class NumDecodings91 {

    /**
     * f(i) 从0到i的字符的编码方法个数
     * if s[i] == '0' 则根据s[i-1]来决定 dp[i]= d[i-2] or 0
     * if 第i-1和第i位组成的数在 10到26之间， dp[i]= d[i-2] + dp[i-1]
     * else dp[i]= d[i-2]
     * 迭代 自顶向下 dp[0] == dp[1] == 1 ?????
     * @author tengyun
     * @date 15:47 2021/1/10
     * @param s 解码字符串
     * @return int
     **/
    public int numDecodings(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        if (n == 0 || charArray[0] == '0') {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 1;
        get(charArray, dp, n);
        return dp[n];
    }

    private int get(char[] charArray, int[] dp, int n) {
        if (dp[n] != -1) {
            return dp[n];
        }
        if (charArray[n-1] == '0') {
            if (charArray[n-2] == '1' || charArray[n-2] == '2') {
                dp[n] = get(charArray, dp, n-2);
            } else {
                dp[n] = 0;
            }
            return dp[n];
        }
        if (charArray[n-2] == '1' || (charArray[n-2] == '2' && charArray[n-1] >= '1' && charArray[n-1] <= '6')) {
            dp[n] = get(charArray, dp, n-2) + get(charArray, dp, n-1);
        } else {
            dp[n] = get(charArray, dp, n-1);
        }
        return dp[n];
    }

    /**
     * 动态规划 自底向上
     * @author tengyun
     * @date 16:00 2021/1/10
     * @param s 解码字符串
     * @return int
     **/
    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if (n == 0 || chars[0] == '0') {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int last = 1, cur = 1;
        for (int i = 1; i < n; i++) {
            int temp = cur;
            if (chars[i] == '0') {
                if (chars[i-1] == '1' || chars[i-1] == '2') {
                    cur = last;
                } else {
                    cur = 0;
                }
            } else {
                if (chars[i-1] == '1' || (chars[i-1] == '2' && chars[i] <= '6' && chars[i] >= '1')) {
                    cur = cur + last;
                }
            }
            last = temp;
        }
        return cur;
    }

}
