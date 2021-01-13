package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/13 19:47
 **/
public class MaxProfit188 {

    public static void main(String[] args) {
        MaxProfit188 maxProfit188 = new MaxProfit188();
        maxProfit188.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3});
    }

    public int maxProfit(int k, int[] prices) {
        int[] buy = new int[k];
        int[] sell = new int[k];
        int n = prices.length;
        // 初始化买入
        for (int j = 0; j <= k - 1; j++) {
            buy[j] = -prices[0];
        }
        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                buy[j] = Math.max(buy[j - 1], sell[j - 1] - prices[i]);
                sell[j] = Math.max(sell[j - 1], buy[j - 1] + prices[i]);
            }
        }
        int max = 0;
        for (int stat : sell) {
            if (stat > max) {
                max = stat;
            }
        }
        return max;
    }

}
