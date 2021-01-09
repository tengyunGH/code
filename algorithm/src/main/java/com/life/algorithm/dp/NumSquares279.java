package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/9 15:05
 **/
public class NumSquares279 {

    public int numSquares(int n) {
        int dp[] = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = n+1;
            for (int j = 1; Math.pow(j, 2) <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-(int)Math.pow(j, 2)]+1);
            }
        }
        return dp[n];
    }
}
