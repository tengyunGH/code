package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/8 20:25
 **/
public class MinPathSum64 {

    public static void main(String[] args) {
        MinPathSum64 minPathSum64 = new MinPathSum64();
        minPathSum64.minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}});
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[2][n];
        dp[0][0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        int last = 0;
        int cur = 1;
        for (int i = 1; i < m; i++) {
            dp[cur][0] = dp[last][0] + grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[cur][j] = Math.min(dp[cur][j-1], dp[last][j]) + grid[i][j];
            }
            int temp = last;
            last = cur;
            cur = temp;
        }
        return dp[last][n-1];
    }
}
