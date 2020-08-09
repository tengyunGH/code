package com.life.algorithm.sort;

/**
 * 插入排序
 * 适合数据量不是很大，数据顺序相差不是太大
 * 从数组的第二位开始，将之插入到已排序数组中的正确位置
 * 根据动图来想
 * 时间复杂度O(n^2)
 * @author tengyun
 * @date 2020/8/9 15:31
 **/
public class InsertionSort {
    public static void main(String[] args) {
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        for (int i = 1; i < a.length; i++) {
            int swap = a[i];
            for (int j = i - 1; j >= 0; j--) {
                if (swap < a[j]) {
                    a[j + 1] = a[j];
                    if (j == 0) {
                        a[0] = swap;
                    }
                } else {
                    a[j + 1] = swap;
                    break;
                }
            }
        }
        System.out.println("结束");
    }
}
