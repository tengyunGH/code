package com.life.algorithm.sort;

/**
 * 希尔排序 改进的插入排序
 * 分组，分很多个组，组内进行插入排序
 * 注意在分组后，进行插入排序的时候，是顺序对每个元素，进行它自己的组内排序
 * 参考 https://mp.weixin.qq.com/s?__biz=MzU1MDE4MzUxNA==&mid=2247484120&idx=1&sn=3e7e1909bce85d860927044728d4d152&scene=21#wechat_redirect
 * @author tengyun
 * @date 2020/8/9 18:44
 **/
public class ShellSort {

    public static void main(String[] args) {
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100, 15};
        int groupNumber = a.length / 2;
        for (; groupNumber > 0; groupNumber = groupNumber / 2) {
            for (int i = groupNumber; i < a.length; i++) {
                insertionSort(a, groupNumber, i);
            }
        }
        System.out.println("结束");
    }

    private static void insertionSort(Integer[] a, int groupNumber, int i) {
        int swap = a[i];
        // 注意j的范围啊， j >= 0
        for (int j = i - groupNumber; j >= 0; j -= groupNumber) {
            //如果小于目标数，则往后挪一个
            if (a[j] > swap) {
                a[j + groupNumber] = a[j];
                // 如果到头了，则第一个就是目标数的位置
                if (j < groupNumber) {
                    a[j] = swap;
                }
             // 如果大于等于目标数，则后面那个就是目标数
            } else {
                a[j + groupNumber] = swap;
                break;
            }
        }
    }
}
