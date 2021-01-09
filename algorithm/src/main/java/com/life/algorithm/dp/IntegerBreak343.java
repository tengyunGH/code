package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/9 14:30
 **/
public class IntegerBreak343 {

    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[2]= 1;
        for (int i = 3; i < n+1; i++) {
            for (int j = 1; j <= i-2; j++) {
                //(i-j) h和 j
                dp[i] = Math.max(dp[i], dp[i-j]*j);
                dp[i] = Math.max(dp[i], (i-j)*j);
            }
        }
        return dp[n];
    }

}
