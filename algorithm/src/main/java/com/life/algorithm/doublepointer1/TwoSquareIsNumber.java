package com.life.algorithm.doublepointer1;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a方 + b方 = c
 * https://leetcode-cn.com/problems/sum-of-square-numbers/  633
 * @author tengyun
 * @version 1.0
 * @date 2020/6/22 11:07
 **/
public class TwoSquareIsNumber {

    public static void main(String[] args) {
        System.out.println(judgeSquareSum(49));
    }

    public static boolean judgeSquareSum(int c) {
        int max = (int) Math.sqrt(c);
        int i = 0, j = max;
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == c) {
                return true;
            } else if (sum < c) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
