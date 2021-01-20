package com.life.algorithm.dp;

/**
 * @author tengyun
 * @date 2021/1/18 19:50
 **/
public class CanJump55 {

    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }
        for (int i = 1; i < n-1; i++) {
            if (nums[i] == 0) {
                // 为0 判断是否可以迈过这个0
                boolean notCan = true;
                for (int j = 1; i-j >= 0; j++) {
                    if (nums[i-j] > j) {
                        notCan = false;
                        break;
                    }
                }
                if (notCan) {
                    return false;
                }
            }
        }
        return true;
    }

}
