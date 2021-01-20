package com.life.algorithm.dp;

import java.util.Arrays;

/**
 * 0-1 背包问题
 * f(i, c) 从0到i个物品中选择若干个物品放入容量为c的包中
 * f(i, c) = Math.max(f(i-1, c) , f(i-1, c-w(i))+v(i))
 * @author tengyun
 * @date 2021/1/18 20:39
 **/
public class Knapsack0_1 {

    private int[][] memo;

    public int mostValue1(int[] weight, int[] value, int c) {
        int n = weight.length;
        memo = new int[n][c+1];
        return bestValue(weight, value, n-1, c);
    }

    private int bestValue(int[] weight, int[] value, int i, int c) {
        if (i < 0 || c <= 0) {
            return 0;
        }
        if (memo[i][c] != 0) {
            return memo[i][c];
        }
        int notSelectedValue = bestValue(weight, value, i-1, c);
        int selectedValue = 0;
        if (c >= weight[i]) {
            selectedValue = bestValue(weight, value, i-1, c-weight[i])+value[i];
        }
        memo[i][c] = Math.max(notSelectedValue, selectedValue);
        return memo[i][c];
    }


    public int mostValue2(int[] weight, int[] value, int c) {
        int n = weight.length;
        memo = new int[n][c+1];
        if (weight[0] >= c) {
            Arrays.fill(memo[0], value[0]);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= c; j++) {
                if (j >= weight[i]) {
                    memo[i][j] = Math.max(memo[i-1][j], memo[i-1][j-weight[i]]+value[i]);
                } else {
                    memo[i][j] = memo[i-1][j];
                }
            }
        }
        return memo[n-1][c];
    }

    public static void main(String[] args) {
        Knapsack0_1 knapsack0_1 = new Knapsack0_1();
        int i = knapsack0_1.mostValue2(new int[]{1, 2, 3}, new int[]{6, 10, 12}, 5);
        System.out.println(i);
    }

}
