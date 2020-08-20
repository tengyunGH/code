package com.life.algorithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 桶排序
 * 1、找出最大最小值
 * 2、根据实际问题确定桶的个数，并划分桶的区间，桶区间等于（最大值 - 最小值）/（桶的个数 - 1）
 * 3、遍历数组，将数据放到合适的桶里
 * 4、将每个桶排序
 * 5、将每个桶合起来
 * @author tengyun
 * @date 2020/8/11 20:35
 **/
public class BucketSort {

    public static void main(String[] args) {
        int[] a = {15, 2, 3, 18, 11};
        // 1、找出最大最小值
        int max = a[0], min = a[0];
        for (int x : a) {
            if (x < min) {
                min = x;
            }
            if (x > max) {
                max = x;
            }
        }
        // 2、桶的个数这里赋值为元素个数
        int bucketNum = a.length;
        int length = (max - min) / (bucketNum - 1);
        // 3、遍历数组，将数据放到合适的桶里
        ArrayList<LinkedList<Integer>> bucketList = new ArrayList<>();
        // 初始化桶
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }
        for (int x : a) {
            bucketList.get((x - min) / length).add(x);
        }
        // 4、桶内排序
        for (List<Integer> oneBucket : bucketList) {
            Collections.sort(oneBucket);
        }
        // 5、输出每个桶的元素到数组中
        int k = 0;
        for (int i = 0; i < bucketNum; i++) {
            for (Integer t : bucketList.get(i)) {
                a[k++] = t + min;
            }
        }
        System.out.println("结束1");
    }

}
