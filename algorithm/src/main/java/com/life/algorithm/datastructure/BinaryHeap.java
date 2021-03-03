package com.life.algorithm.datastructure;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 二叉堆
 * 对二叉堆的操作：构建操作 插入操作 删除操作
 * 如何存储：数组，父节点的位置为n，左子节点为2n+1，右子节点为2n+2
 * 类型：最大堆，最小堆
 * @author tengyun
 * @date 2020/8/12 13:40
 **/
public class BinaryHeap {

    public static void main(String[] args) {
        Integer[] a = {15, 2, 3, 18, 11, 90, 67, 34, 9, 23, 90, 10};
        // minimumHeap(a);
        // maximumHeap(a);
        // minimumHeapSort(a);
        // maxmumHeapSort(a);
        System.out.println("结束1");
    }

    // 从大到小排序
    private static void maxmumHeapSort(Integer[] a) {
        int length = a.length;
        int z = 0;
        while (z < length - 1) {
            // 本棵树中，从最后一个非叶子结点开始循环
            int i = (length - 2 - z) / 2 + z;
            while (i >= z) {
                // 将这棵子树的顶点进行下沉，父节点小于最大的那个就下沉
                int temp = a[i];
                int j = i;
                while (true) {
                    // 当j指向的是叶子结点，结束；当父节点就是最大的时候，结束；
                    if (j > ((length - 2 - z) / 2 + z)) {
                        a[j] = temp;
                        break;
                    }
                    int maxmumIndex = 2 * j - z + 1, maxmum = a[maxmumIndex];
                    if (maxmumIndex + 1 < length && a[maxmumIndex + 1] > maxmum) {
                        maxmum = a[++maxmumIndex];
                    }
                    // 更大的上浮，小的下沉
                    if (temp < maxmum) {
                        a[j] = maxmum;
                        j = maxmumIndex;
                    } else {
                        a[j] = temp;
                        break;
                    }
                }
                i--;
            }
            z++;
        }
        System.out.println("结束1");
    }

    /**
     * 一定记住最后一个非叶子节点的下标是，数组个数减去2除以2（数组下标从0开始）
     **/
    private static void minimumHeapSort(Integer[] a) {
        int length = a.length;
        int z = 0;
        // 每次找到最小的那个数之后放在数组头部
        while (z < length - 1) {
            // 从本棵树的最后一个非叶子节点开始循环，逐个下沉，注意最后一个非叶子节点的下标表达式
            int i = (length - 2 - z) / 2 + z ;
            while (i >= z) {
                int temp = a[i];
                int j = i;
                // 当j是叶子节点时结束循环，当j不比两个子节点大的时候结束循环
                while (true) {
                    if (j > (length - 2 - z) / 2 + z) {
                        a[j] = temp;
                        break;
                    }
                    int minimumIndex = 2 * j - z + 1, minimum = a[minimumIndex];
                    if (minimumIndex + 1 < length && a[minimumIndex + 1] < minimum) {
                        minimumIndex++;
                        minimum = a[minimumIndex];
                    }
                    // 父节点比子节点要大，下沉
                    if (temp > minimum) {
                        a[j] = minimum;
                        j = minimumIndex;
                    } else {
                        a[j] = temp;
                        break;
                    }
                }
                i--;
            }
            z++;
        }
        System.out.println("结束1");
    }

    /**
     * 构建最小堆（父节点比子节点小）
     * 所有非叶子节点依次“下沉”
     * 其实是让最小的那棵树成为最小堆，然后再往上一层，把顶部元素加上后，再次形成一个最小堆，最终的最终，就成为一个整个的最小堆了
     * @author tengyun
     * @date 17:19 2020/8/12
     **/
    public static void minimumHeap(Integer[] a) {
        int length = a.length;
        // j初始化为最后一个非叶子节点
        for (int j = (length - 2) / 2; j >= 0 ; j--) {
            // temp就是要下沉的节点，每次和子节点比较大小的时候应该是temp来比较，
            // 当temp不比子节点大的时候，那个位置就是temp的位置了
            int temp = a[j];
            int i = j;
            // 当i不是叶子节点时做下沉
            while (i <=  (length - 2) / 2) {
                // 选出子节点中更小的那个
                int smallerIndex = 2 * i + 1, smaller = a[smallerIndex];
                if ((2 * i + 2) < length && smaller > a[2 * i + 2]) {
                    smallerIndex = 2 * i + 2;
                    smaller = a[smallerIndex];
                }
                // 如果比最小的要大，则本节点下沉，注意这里得拿着那个要下沉的元素来比较，看这个元素是否要待在a[i]这个位置
                if (temp > smaller) {
                    a[i] = smaller;
                    i = smallerIndex;
                } else {
                    // 不小于最小的那个，说明这个节点和他的子节点已经构成了最小堆，结束本次循环
                    // 此时i这个位置就是“顶点”元素的位置了
                    break;
                }
            }
            a[i] = temp;
        }
        System.out.println("结束1");
    }

    /**
     * 构建最大堆
     * 将小的元素下沉
     * @author tengyun
     * @date 21:21 2020/8/13
     * @param a
     **/
    public static void maximumHeap(Integer[] a) {
        int length = a.length;
        for (int i = (a.length - 2) / 2; i >= 0 ; i--) {
            int temp = a[i];
            int j = i;
            while (j <= (a.length - 2) / 2) {
                int biggerIndex = 2 * j + 1, bigger = a[2 * j + 1];
                if ((2 * j + 2) < length && bigger < a[2 * j + 2]) {
                    biggerIndex = 2 * j + 2;
                    bigger = a[biggerIndex];
                }
                if (temp < bigger) {
                    a[j] = bigger;
                    j = biggerIndex;
                }
            }
            a[j] = temp;
        }
        System.out.println("结束");
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
