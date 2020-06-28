package com.life.algorithm.doublepointer1;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数
 * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/   167
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * @author tengyun
 * @version 1.0
 * @date 2020/6/22 11:01
 **/
public class TwoSum {

    public static void main(String[] args) {
        System.out.println(twoSum(new int[]{1,2,3,4,5,6,7,8,11,23,45,56,67,78,89,90,100,110,120,140}, 89));
    }

    /**
     * @author tengyun
     * @date 11:02 2020/6/22
     * @param numbers 有序数组
     * @param target 目标和
     * @return int[] 下标
     **/
    public static int[] twoSum(int[] numbers, int target) {
        int length = numbers.length;
        int[] results = new int[2];
        for (int i = 0, j = length - 1; i < j;) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                results[0] = i + 1;
                results[1] = j + 1;
                break;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return results;
    }

}
