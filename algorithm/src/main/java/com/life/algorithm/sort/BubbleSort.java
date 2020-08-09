package com.life.algorithm.sort;

/**
 * 冒泡排序算法
 * 每经过一次循环把最大的那个放到乱序数组的最后边
 * 时间复杂度 O(n^2)
 * @author tengyun
 * @date 2020/8/9 18:08
 **/
public class BubbleSort {

    public static void main(String[] args) {
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        int temp;
        for(int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        System.out.println("结束");
    }

}
