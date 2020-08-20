package com.life.algorithm.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 基数排序
 * 先按照个位数进行桶排序，然后将每个桶中的数排好序，然后再按照十位数进行桶排序，然后再排好序
 * 所以需要找到最大值
 * @author tengyun
 * @date 2020/8/20 11:09
 **/
public class BaseSort {

    public static void main(String[] args) {
        baseSort(new int[]{12});
    }

    public static void baseSort(int[] nums) {
        // 先找到最大值，确认桶排序次数
        int max = nums[0];
        for (int num :nums) {
            if (num > max) {
                max = num;
            }
        }
        // 找到基数位
        int base = 0;
        while (max > 0) {
            max /= 10;
            base++;
        }
        // 桶排序
        int k = 0, baseValue = 1;
        while (k < base) {
            // nums[k]的值的第k+1位为下标，值为value
            int i = 0;
            ArrayList<Integer>[] buckets = (ArrayList<Integer>[]) Array.newInstance(ArrayList.class, 10);
            while (i < nums.length) {
                int subscript = (nums[i] / baseValue) % 10;
                ArrayList<Integer> values = buckets[subscript];
                if (values == null) {
                    values = new ArrayList<>();
                    buckets[subscript] = values;
                }
                values.add(nums[i]);
                i++;

            }
            int j = 0;
            for (int z = 0; z < buckets.length; z++) {
                if (buckets[z] != null) {
                    ArrayList<Integer> values = buckets[z];
                    for (Integer value : values) {
                        nums[j++] = value;
                    }
                }
            }
            baseValue *= 10;
            k++;
        }
        System.out.println("结束1");
    }

}
