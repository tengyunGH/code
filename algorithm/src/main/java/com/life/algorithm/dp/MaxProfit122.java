package com.life.algorithm.dp;

/**
 * 不限制交易次数，不限制亏损还是收益
 * @author tengyun
 * @date 2021/1/13 14:37
 **/
public class MaxProfit122 {

    /** 方法一 贪心算法
     * 因为交易次数不受到限制，所以只要上坡就计算收益，那么最后的肯定就是最大收益
     **/
    public int maxProfit(int[] prices) {
        int not = 0, yes = -prices[0];
        int n = prices.length;
        int profit = 0;
        for (int i = 1; i < n; i++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }


    /**
     * 方法2 动态规划
     * f(i)[0] 第i天结束后不持有股票
     * f(i)[1] 第i天结束后持有股票
     * f(i)[0] = Math.max(f(i-1)[0], f(i-1)[1]+c[i]);
     * f(i)[1] = Math.max(f(i-1)[1], f(i-1)[0]-c[i]);
     * f(0)[0] = 0;
     * f(0)[1] = -c[0];
     **/
    public int maxProfit2(int[] prices) {
        int not = 0, yes = -prices[0];
        int n = prices.length;
        int notToday = 0, yesToday = 0;
        for (int i = 1; i < n; i++) {
            notToday = Math.max(not, yes+prices[i]);
            yesToday = Math.max(yes, not-prices[i]);
            not = notToday;
            yes = yesToday;
        }
        return not;
    }
}
