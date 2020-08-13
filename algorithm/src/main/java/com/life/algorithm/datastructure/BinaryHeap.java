package com.life.algorithm.datastructure;

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
        Integer[] a = {15, 2, 31, 18, 1, 90, 67, 34, 9, 23, 90, 100};
        minimumHeap(a);
        maximumHeap(a);
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



}
