package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/10 17:27
 **/
public class UniquePathsWithObstacles63 {

    /**
     * 障碍物的地方，为0
     * 那么i==0和j==0就要放在循环里面考虑了
     * 将last初值设置为1，那么在计算i == 0 的时候，可以直接使用 dp[0][j] = dp[cur][j-1] + dp[last][j]; 了，反正此时 dp[last]都是等于0的。
     * @author tengyun
     * @date 17:28 2021/1/10
     * @param obstacleGrid 障碍物数组
     * @return int
     **/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n =obstacleGrid[0].length;
        int[][] dp = new int[2][n];
        int last = 1, cur = 0;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[cur][j] = 0;
                } else {
                    if (i == 0 && j == 0) {
                        dp[0][0] = 1;
                    } else if (j == 0) {
                        dp[cur][j] = dp[last][j];
                    } else {
                        dp[cur][j] = dp[cur][j-1] + dp[last][j];
                    }
                }
            }
            int temp = last;
            last = cur;
            cur = temp;
        }
        return dp[last][n-1];
    }

}
