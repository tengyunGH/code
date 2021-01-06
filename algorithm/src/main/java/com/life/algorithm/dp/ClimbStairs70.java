package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/6 15:12
 **/
public class ClimbStairs70 {

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
