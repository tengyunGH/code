package com.life.algorithm.sort;

/**
 * 选择排序
 * 找到数组中最小的元素，将它与第一个交换位置，然后继续选择剩余中最小的，与第二个交换，如此往复
 * 时间复杂度 O(n^2)  n（n/2）
 * @author tengyun
 * @date 2020/8/9 14:53
 **/
public class SelectionSort {
    public static void main(String[] args) {
        Integer[] a = {15,2,31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        // 第一层循环，i小于a.length - 1，因为循环到最后一个的时候，只剩他自己了
        for (int i = 0; i < a.length - 1; i++) {
            int smallIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[smallIndex]) {
                    smallIndex = j;
                }
            }
            int temp = a[i];
            a[i] = a[smallIndex];
            a[smallIndex] = temp;
        }
        System.out.println("结束");
    }
}
