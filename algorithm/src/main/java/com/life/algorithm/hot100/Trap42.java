package com.life.algorithm.hot100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tengyun
 * @date 2021/1/29 20:25
 **/
public class Trap42 {

    /**
     * 一个点的雨水量等于 左右两边最大的较小值减去当前值
     * @author tengyun
     * @date 14:01 2021/2/18
     * @param height 高度数组
     * @return int
     **/
    public int trap2(int[] height) {
        int res = 0, n = height.length;
        if (n <= 2) {
            return res;
        }
        // 计算每个位置的最右边的最大值
        int[] rmax = new int[n];
        for (int i = n-1; i >= 0; i--) {
            rmax[i] = Math.max(rmax[i+1], height[i]);
        }
        int lmax = height[0];
        for (int i = 1; i < n; i++) {
            lmax = Math.max(lmax, height[i]);
            res += Math.min(lmax, rmax[i]) - height[i];
        }
        return res;
    }





    /**
     * 对第i个元素能接的雨水，等于 min(max(height[l]), max(height[r])) - height[i])
     * 暴利解法
     *
     * @author tengyun
     * @date 20:37 2021/1/29
     **/
    public int trap(int[] height) {
        int n = height.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 找到max(l)
            int lMax = height[i], rMax = height[i];
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > lMax) {
                    lMax = height[j];
                }
            }
            // 找到max(r)
            for (int j = i + 1; j < n; j++) {
                if (height[j] > rMax) {
                    rMax = height[j];
                }
            }
            res += Math.min(lMax, rMax) - height[i];
        }
        return res;
    }

    /**
     * 先将每个元素的左最大值和右最大值计算出来，避免重复计算
     *
     * @author tengyun
     * @date 20:37 2021/1/29
     **/
    public int trap1(int[] height) {
        int n = height.length;
        int res = 0;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        lMax[0] = height[0];
        rMax[n-1] = height[n-1];
        for (int j = 1; j < n; j++) {
            lMax[j] = Math.max(height[j], lMax[j-1]);
        }
        for (int j = n-2; j >= 0; j--) {
            rMax[j] = Math.max(height[j], rMax[j+1]);
        }
        for (int i = 0; i < n; i++) {
            res += Math.min(lMax[i], rMax[i]) - height[i];
        }
        return res;
    }

    public int trap3(int[] height) {
        int res = 0, n = height.length;
        if (n <= 2) {
            return res;
        }
        // 使用栈
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // 栈为空，进栈
            if (stack.isEmpty()) {
                stack.addLast(height[i]);
            } else {
                // 栈不为空，比较栈顶元素和当前元素的大小
                Integer peek = stack.peekLast();
                if (height[i] <= peek) {
                    stack.addLast(height[i]);
                } else {


                }
            }
        }



        return res;
    }



}
