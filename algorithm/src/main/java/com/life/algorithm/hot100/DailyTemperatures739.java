package com.life.algorithm.hot100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tengyun
 * @date 2021/1/29 19:49
 **/
public class DailyTemperatures739 {

    /**
     * 暴利解法，对每个元素，遍历一遍，找到比它大的，结束循环
     * O(n^2)
     *
     * @param T 温度数组
     * @return int[]
     * @author tengyun
     * @date 11:07 2021/2/18
     **/
    public int[] dailyTemperatures1(int[] T) {
        int n = T.length;
        int[] length = new int[n];
        if (n == 1) {
            return length;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (T[j] > T[i]) {
                    length[i] = j - i;
                    break;
                }
            }
        }
        return length;
    }

    /**
     * 单调栈解法
     * 创建一个栈
     * 遍历数组
     * 当栈为空时，将数组元素的下标入栈
     * 当栈不为空时，比较栈顶peekIndex对应的温度 T[peekIndex] 和 T[i]的大小
     * T[peekIndex] > T[i]  那么第peekIndex这个元素需要过i-peekIndex能找到比她高的温度
     * T[peekIndex] <= T[i] 那么入栈
     * @param T 温度数组
     * @return int[]
     * @author tengyun
     * @date 11:08 2021/2/18
     **/
    public int[] dailyTemperatures2(int[] T) {
        int n = T.length;
        int[] result = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty()) {
                stack.addLast(i);
            } else {
                while (!stack.isEmpty() && T[stack.peekLast()] < T[i]) {
                    Integer poll = stack.pollLast();
                    result[poll] = i-poll;
                }
                stack.addLast(i);
            }
        }
        return result;
    }

}
