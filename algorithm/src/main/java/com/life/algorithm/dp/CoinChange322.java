package com.life.algorithm.dp;

import java.util.Arrays;

/**
 * @author tengyun
 * @date 2021/1/4 19:29
 **/
public class CoinChange322 {

    public static void main(String[] args) {
        CoinChange322 coinChange322 = new CoinChange322();
        int[] coins = new int[]{186,419,83,408};
        int num = coinChange322.coinChange(coins, 6249);
        System.out.println(num);
    }


    public int coinChange(int[] coins, int amount) {
        int[]dp = new int[amount + 1];
        if (amount == 0) {
            return 0;
        }
        if (amount< 0) {
            return -1;
        }
        return get(coins, amount, dp);
    }

    public int get(int[] coins, int amount, int[] dp) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (dp[amount] != 0) {
            return dp[amount];
        }
        int min = amount + 1;
        int num;
        for (int coin : coins) {
            num = get(coins, amount - coin, dp);
            if (num == -1) {
                continue;
            }
            min = Math.min(min, num);
        }
        if (min == amount + 1) {
            dp[amount] = -1;
        } else {
            dp[amount] = min;
        }
        return min;
    }




    public int coinChange2(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            // for (int int coin : coins) 比 for (int j = 0 ; j < coins.length; j++) 要快那么一点点
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
