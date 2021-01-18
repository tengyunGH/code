package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/13 19:47
 **/
public class MaxProfit188 {

    public static void main(String[] args) {
        MaxProfit188 maxProfit188 = new MaxProfit188();
        maxProfit188.maxProfit(1, new int[]{1,2});
    }

    /**
     *
     **/
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        if (k == 0 || n <= 1) {
            return 0;
        }

        int[][] buy = new int[n][k+1];
        int[][] sell = new int[n][k+1];

        // 初始化买入
        for (int j = 1; j <= k; j++) {
            buy[0][j] = -prices[0];
        }
        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k && j <= (n/2); j++) {
                // 第i天买入第j支
                buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j-1] - prices[i]);
                // 第i天卖出第j支
                sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j] + prices[i]);
            }
        }
        int max = 0;
        for (int stat : sell[n-1]) {
            if (stat > max) {
                max = stat;
            }
        }
        return max;
    }

}
