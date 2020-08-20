package com.life.algorithm.sort;

/**
 * 堆排序
 * @author tengyun
 * @date 13:01 2020/8/18
 **/
public class HeapSort {

    public static void main(String[] args) {
        Integer[] a = {-1, 15, 0};
        // 将顶点元素与未排序的最后一个交换，然后再做构建大顶堆操作
        int z = a.length - 1;
        while (z > 0) {
            downAdjust(a, z);
            int temp = a[0];
            a[0] = a[z];
            a[z] = temp;
            z--;
        }
        System.out.println("结束1");
    }

    /**
     *
     * @author tengyun
     * @date 14:01 2020/8/18
     **/
    private static void downAdjust(Integer[] a, int last) {
        if (last == 0) {
            return;
        }
        // 最后一个非叶子节点坐标
        int i = (last - 1) / 2;
        while (i >= 0) {
            // 临时保存下沉元素
            int temp = a[i];
            int j = i;
            while (true) {
                // 如果要下沉的位置是叶子节点了，则不会再下沉了
                if (j > (last - 1) / 2) {
                    a[j] = temp;
                    break;
                }
                // 挑出子节点中最大的那个
                int maxmunIndex = 2 * j + 1, maxmum = a[maxmunIndex];
                if (maxmunIndex + 1 <= last && a[maxmunIndex + 1] > maxmum) {
                    maxmum = a[++maxmunIndex];
                }
                // 和要下沉的元素比较，如果子节点大，则下沉（子节点上浮），否则已经是找到了要下沉的位置了
                if (temp < maxmum) {
                    a[j] = maxmum;
                    j = maxmunIndex;
                } else {
                    a[j] = temp;
                    break;
                }
            }
            i--;
        }
    }


}
