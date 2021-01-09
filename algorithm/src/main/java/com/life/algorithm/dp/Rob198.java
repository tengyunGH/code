package com.life.algorithm.dp;

import java.util.Arrays;

/**
 * f(i) = Math.max(v(i)+f(i+2), v(i+1)+f(i+3))
 * @author tengyun
 * @date 2021/1/9 17:26
 **/
public class Rob198 {

    /**
     * 递归，自顶向下
     * @author tengyun
     * @date 17:27 2021/1/9
     * @param nums 房子价值数组
     * @return int
     **/
    public int rob1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[n] = 0;
        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-1], nums[n-2]);
        get(nums, 0, dp);
        return dp[0];
    }

    public int get(int[] nums, int i, int[] dp) {
        if (dp[i] != -1) {
            return dp[i];
        }
        dp[i] = Math.max(nums[i]+get(nums, i+2, dp), nums[i+1]+get(nums, i+3, dp));
        return dp[i];
    }

    /**
     * 自底向上 动态规划
     * @author tengyun
     * @date 17:29 2021/1/9
     * @param nums 房子价值数组
     * @return int
     **/
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[n] = 0;
        dp[n-1] = nums[n-1];
        dp[n-2] = Math.max(nums[n-1], nums[n-2]);
        for (int i = n-3; i >= 0; i--) {
            dp[i] = Math.max(nums[i]+dp[i+2], nums[i+1]+dp[i+3]);
        }
        return dp[0];
    }

    // 将一维dp数组用四个常数代替
    public int rob3(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        int temp;
        int lastlast = 0;
        int last = nums[n-1];
        int cur = Math.max(nums[n-2], nums[n-1]);
        if (n == 2) {
            return cur;
        }
        for (int i = n-3; i >= 0; i--) {
            temp = cur;
            cur = Math.max(nums[i]+last, nums[i+1]+lastlast);
            lastlast = last;
            last = temp;
        }
        return cur;
    }
}
