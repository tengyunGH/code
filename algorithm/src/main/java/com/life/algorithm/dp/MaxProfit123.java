package com.life.algorithm.dp;

/**
 * 限制为两笔交易，买入前必须都卖出
 * @author tengyun
 * @date 2021/1/13 14:48
 **/
public class MaxProfit123 {

    /**
     * 动态规划
     * 有五种状态
     * 第i天结束后，没有进行过任何交易
     * 第i天结束后，已经买入了第一次
     * 第i天结束后，已经卖出了第一次
     * 第i天结束后，已经买入了第二次
     * 第i天结束后，已经卖出第二次
     **/
    public int maxProfit(int[] prices) {
        int buy1 = -prices[0], sell1 = 0, buy2 = -prices[0], sell2 = 0, n = prices.length;
        for (int i = 1; i < n; i++) {
            sell2 = Math.max(sell2, buy2+prices[i]);
            buy2 = Math.max(buy2, sell1-prices[i]);
            sell1 = Math.max(sell1, buy1+prices[i]);
            buy1 = Math.max(buy1, -prices[i]);
        }
        return Math.max(sell1, sell2);
    }




}
