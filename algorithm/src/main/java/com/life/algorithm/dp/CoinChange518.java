package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/6 13:52
 **/
public class CoinChange518 {

    public static void main(String[] args) {
        CoinChange518 coinChange518 = new CoinChange518();
        int change = coinChange518.change(11, new int[]{1, 3, 5});
        System.out.println(change);
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] += dp[i-coin];
                }
            }
        }
        return dp[amount];
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

}
