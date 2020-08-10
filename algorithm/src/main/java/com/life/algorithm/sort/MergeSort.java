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
        /**
         * 递归
         * 传入子数组（原数组，边界）
         * 若子数组长度等于1，返回（递归终止条件）
         * 若子数组长度大于1，将数组分成两半，每边都进行归并排序
         * 两半排好序之后，将整个子数组进行归并排序
         **/
        mergeSortRecursion(a, 0, a.length - 1);
        System.out.println("结束");

        Integer[] b = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        /**
         * 不递归
         * 外层循环：子数组的长度为 1 2 4 8 16 。。。。
         * 里层循环：子数组长度确定了，每两个子数组进行一下归并排序
         * 注意1：当最后一个子数组的长度在i到2i之间时，注意right的指针要指向数组最后一位再进行归并排序
         * 注意2：外层循环的i的取值范围。数组长度一般不会正好整除2
         **/
        // 每次合并的数组的长度为 1,2,4, 8,.....
        // 注意i的取值范围
        for (int i = 1; i < b.length; i *= 2) {
            // 本循环执行的子数组个数为i，所以i的取值范围不能是i<=b.length/2，因为个数是13个的话，i大于6就会终止了
            int left = 0, center = left + i - 1, right = center + i;
            // 对每2个组进行归并排序
            while (right < b.length) {
                merge(b, left, center, right);
                left = right + 1;
                center = left + i - 1;
                right = center + i;
            }
            // 出了这个循环，有三种情况，边界<left；left <= 边界 <= center；center < 边界 < right；
            // 只有 center < 边界，这种情况，说明剩下两个组，但是最后一组的个数小于i，调整right指针
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

    /**
     * 将两个有序数组合并为一个数组
     * 将a中的后边那个数组复制出来，作为要填充的数组temp
     * 从后往前比较，谁大谁就填充a的后边
     * 要注意两点：1、temp填补完毕，结束循环；2、若是a的前边那个数组先填充完，那就将temp剩余的都填充到前面
     * @param a 原数组
     * @param left 左边界指针
     * @param center 中心指针
     * @param right 右指针
     **/
    private static void merge(Integer[] a, int left, int center, int right) {
        // 将后面这个数组先复制出来
        Integer[] temp = new Integer[right - center];
        for (int i = 0; i < right - center; i++) {
            temp[i] = a[center + 1 + i];
        }
        // 此时要合并a的前半部分和temp这个数组到a里面去
        int i = temp.length - 1;
        while(left <= right) {
            if (i < 0) {
                break;
            }
            if (center < left) {
                a[right] = temp[i];
                i--;
                continue;
            }
            if (a[center] > temp[i]) {
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
