package com.life.algorithm.sort;

/**
 * 归并排序
 * 将数组分成两个，再每个分成两个，当子数组元素个数为1的时候，进行两两的归并排序，最终形成有序的一个数组
 * 时间复杂度：
 * @author tengyun
 * @date 2020/8/10 9:06
 **/
public class MergeSort {

    public static void main(String[] args) {
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        mergeSort(a, 0, a.length - 1);
        System.out.println("结束");
    }

    private static void mergeSort(Integer[] a, int left, int right) {
        if (left == right) {
            return;
        }
        int center = (left + right) / 2;
        mergeSort(a, left, center);
        mergeSort(a, center + 1, right);
        merge(a, left, center, right);
    }

    private static void merge(Integer[] a, int left, int center, int right) {
        // 将后面这个数组先复制出来
        Integer[] temp = new Integer[right - center];
        for (int i = 0; i < right - center; i++) {
            temp[i] = a[center + 1 + i];
        }
        // 此时要合并a的前半部分和temp这个数组到a里面去
        int i = temp.length - 1;
        while(left <= right) {
            if (i < 0 || (center >= left && a[center] > temp[i])) {
                a[right] = a[center];
                center--;
            } else {
                a[right] = temp[i];
                i--;
            }
            right--;
        }

    }


}
