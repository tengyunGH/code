package com.life.algorithm.sort;

/**
 * 归并排序
 * 将数组分成两个，再每个分成两个，当子数组元素个数为1的时候，进行两两的归并排序，最终形成有序的一个数组
 * 时间复杂度：nlogn
 * @author tengyun
 * @date 2020/8/10 9:06
 **/
public class MergeSort {

    public static void main(String[] args) {
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        mergeSortRecursion(a, 0, a.length - 1);
        System.out.println("结束");

        Integer[] b = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        // 每次合并的数组的长度为 1,2,4, 8,.....
        // 注意i的取值范围
        for (int i = 1; i < b.length; i *= 2) {
            // 本循环执行的子数组个数为i，所以i的取值范围不能是i<=b.length/2，因为个数是13个的话，i大于6就会终止了
            int left = 0, center = left + i - 1, right = center + i;
            // 对每个组进行归并排序
            while (right < b.length) {
                merge(b, left, center, right);
                left = right + 1;
                center = left + i - 1;
                right = center + i;
            }
            // 若最后一个分组的长度比i小
            if (center < b.length - 1) {
                right = b.length - 1;
                merge(b, left, center, right);
            }
        }
        System.out.println("结束");
    }

    private static void mergeSortRecursion(Integer[] a, int left, int right) {
        if (left == right) {
            return;
        }
        int center = (left + right) / 2;
        mergeSortRecursion(a, left, center);
        mergeSortRecursion(a, center + 1, right);
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
