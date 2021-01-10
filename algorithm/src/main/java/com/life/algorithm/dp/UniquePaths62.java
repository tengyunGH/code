package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/10 16:23
 **/
public class UniquePaths62 {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        get(m-1, n-1, dp);
        return dp[m-1][n-1];
    }

    public int get(int i, int j, int[][] dp) {
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        if (i == 0) {
            dp[i][j] = get(i, j-1, dp);
        } else if (j == 0) {
            dp[i][j] = get(i-1, j, dp);
        } else {
            dp[i][j] = get(i-1, j, dp) + get(i, j-1, dp);
        }
        return dp[i][j];
    }

}
