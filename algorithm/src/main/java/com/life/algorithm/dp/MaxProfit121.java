package com.life.algorithm.dp;

/**
 * 只做一次交易
 * 第i天的最大收益是，第i天的值减去从0到第i-1之间的最小值
 * 记录下最大的收益就好了
 * @author tengyun
 * @date 2021/1/12 20:47
 **/
public class MaxProfit121 {

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int min = prices[0], maxGap = 0;
        for (int i = 1; i < n; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else {
                int gap = prices[i] - min;
                if (gap > maxGap) {
                    maxGap = gap;
                }
            }
        }
        return maxGap;
    }
}
