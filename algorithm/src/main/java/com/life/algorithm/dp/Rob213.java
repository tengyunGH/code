package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/11 8:33
 **/
public class Rob213 {

    /**
     * 分为两种情况考虑，偷0号房间，那么n-1是不能偷的， 偷n-1号房间，那么0号房间是不能偷的。
     * f(i) = Math.max(f(i-1), f(i-2)+nums[i])
     * 降维空间：当前之和前两个相关。
     * @author tengyun
     * @date 9:03 2021/1/11
     * @param nums 房屋价值数组
     * @return int
     **/
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        return Math.max(rob(nums, 0, n-2), rob(nums, 1, n-1));
    }

    public int rob(int[] nums, int left, int right) {
        if (right == left) {
            return nums[left];
        }
        int last = nums[left], cur = Math.max(nums[left], nums[left+1]), temp;
        for (int i = left+2; i <= right; i++) {
            temp = cur;
            cur = Math.max(last+nums[i], cur);
            last = temp;
        }
        return cur;
    }

}
