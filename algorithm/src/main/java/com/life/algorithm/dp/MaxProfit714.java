package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/14 20:03
 **/
public class MaxProfit714 {

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        // 今天结束后，持有一支
        int buy = -prices[0]-fee;
        // 今天结束后，不持有一支
        int sell = 0;
        for (int i = 1; i < n; i++) {
            int buyNew = Math.max(buy, sell-prices[i]-fee);
            int sellNew = Math.max(sell, buy+prices[i]);
            buy = buyNew;
            sell = sellNew;
        }
        return sell;
    }

}
