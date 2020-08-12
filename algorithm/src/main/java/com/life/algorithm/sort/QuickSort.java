package com.life.algorithm.sort;

import java.util.Random;

/**
 * 快速排序
 * 1、随机找到一个主元
 * 2、确定主元的位置，使得主元左边的比主元小，主元右边的比主元大
 * 3、对主元左边的和右边的分别使用快速排序
 *
 *
 * 主元的位置决定了 i j指针谁先移动，主元在左边，j先移动，主元在右边，i先移动
 * @author tengyun
 * @date 2020/8/10 20:26
 **/
public class QuickSort {

    private static Random random = new Random();

    public static void main(String[] args) {
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        quickSort(a, 0, a.length - 1);
        System.out.println("结束");
    }

    private static int getRandom(int left, int right) {
        return left + random.nextInt(right - left);
    }

    private static void quickSort(Integer[] a, int left, int right) {
        if (right - left <= 0) {
            return;
        }
        int i = left, j = right;
        while(i < j) {
            while (i < j && a[j] >= a[left]) {
                j--;
            }
            while (i < j && a[i] <= a[left]) {
                i++;
            }
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i];
        a[i] = a[left];
        a[left] = temp;
        quickSort(a, left, i - 1);
        quickSort(a, i + 1, right);
    }


}
