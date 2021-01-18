package com.life.algorithm.dp;

import java.util.Arrays;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2,从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * @author tengyun
 * @date 2021/1/17 14:08
 **/
public class Jump45 {

    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, n + 1);
        if (n <= 0) {
            return 0;
        }
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i] && (i + j) < n; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        Jump45 jump45 = new Jump45();
        jump45.jump2(new int[]{2,3,1,1,4});
    }

    public int jump2(int[] nums) {
        int n = nums.length;
        int num = 0;
        for (int i = 0; i < n;) {
            int step = nums[i];
            int max = 0;
            int nextI = 0;
            for (int j = 1; j <= step; j++) {
                if (i + j >= n-1) {
                    return ++num;
                }
                if ((i+j+nums[i+j]) > max) {
                    max = i+j+nums[i+j];
                    nextI = i+j;
                }
            }
            num++;
            i = nextI;
        }
        return num;
    }

}
