package com.life.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 快速排序
 * 1、随机找到一个主元
 * 2、确定主元的位置，使得主元左边的比主元小，主元右边的比主元大
 * 3、对主元左边的和右边的分别使用快速排序
 * <p>
 * <p>
 * 主元的位置决定了 i j指针谁先移动，主元在左边，j先移动，主元在右边，i先移动
 *
 * @author tengyun
 * @date 2020/8/10 20:26
 **/
public class QuickSort {

    private static Random random = new Random();

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        List<Integer> result = new ArrayList<>();
        rotate(a, 3);
        System.out.println("结束");
    }


    public static void rotate(int[] nums, int k) {
        if (k <= 0 || nums.length <= 1) {
            return;
        }
        k = k % nums.length;
        int temp = 0;
        int n = nums.length - 1;
        for (int i = 0; i < k; i++) {
            temp = nums[n - i];
            nums[n - i] = nums[i];
            nums[i] = temp;
        }
    }

    public static int hIndex(int[] citations) {
        // 先将大于n的变成n
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) {
                citations[i] = n;
            }
        }
        // 使用计数法，以数值为index，value为出现的次数
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int index = citations[i];
            count[index]++;
        }
        // 遍历count数组，从后往前累加，累加的值，就是引用次数大于i的篇数
        int s, h = 0;
        for (int i = n; i >= 0; i--) {
            s = +count[i];
            if (s >= i) {
                h = s;
                break;
            }
        }
        return h;

    }

    private static int getRandom(int left, int right) {
        return left + random.nextInt(right - left);
    }

    public static int findShortestSubArray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            leftMap.putIfAbsent(nums[i], i);
            rightMap.put(nums[i], i);
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }
        int degree = Collections.max(countMap.values());
        int min = nums.length;
        for (Integer num : leftMap.keySet()) {
            if (countMap.get(num) == degree) {
                int distance = rightMap.get(num) - leftMap.get(num) + 1;
                if (distance < min) {
                    min = distance;
                }
            }
        }
        return min;
    }

    private static void quickSort(Integer[] a, int left, int right) {
        if (right - left <= 0) {
            return;
        }
        int i = left, j = right;
        while (i < j) {
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




    public void quickSort2(Integer[] a, int left, int right) {
        if (right <= left) {
            return;
        }
        int i = left, j = right;
        while (i < j) {
            while (i < j && a[j] >= a[left]) {
                j--;
            }
            while (i < j && a[i] <= a[left]) {
                i++;
            }
            if (i < j) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
            }
        }
        int temp = a[i];
        a[i] = a[left];
        a[left] = temp;
        quickSort(a, left, i-1);
        quickSort(a, i+1, right);
    }













}
