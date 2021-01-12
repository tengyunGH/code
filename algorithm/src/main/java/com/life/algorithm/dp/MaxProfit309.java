package com.life.algorithm.dp;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * @author tengyun
 * @date 2021/1/12 9:11
 **/
public class MaxProfit309 {

    public static void main(String[] args) {
        MaxProfit309 maxProfit309 = new MaxProfit309();
        maxProfit309.maxProfit(new int[]{1,2,3,0,2});
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) {
            return 0;
        }
        int threeYes = 0, twoYes = 0;
        int oneYes = -prices[0];
        int oneToday, twoToday = 0, threeToday = 0;
        for (int i = 1; i < n; i++) {
            oneToday = Math.max(oneYes, (threeYes-prices[i]));
            twoToday = oneYes+prices[i];
            threeToday = Math.max(twoYes, threeYes);
            oneYes = oneToday;
            twoYes = twoToday;
            threeYes = threeToday;
        }
        return Math.max(twoToday, threeToday);


    }

}
