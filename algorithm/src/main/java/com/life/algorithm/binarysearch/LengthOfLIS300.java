package com.life.algorithm.binarysearch;

/**
 * 贪心思想和二分查找
 * 无序中找出有序的递增子序列
 * 尽可能的让下一个有序递增子序列的下一个值要小一些，这样才能保证更长
 * d[i]表示长度为i的递增子序列的最小值
 * 遍历nums
 * 如果nums[i]>d[len],则直接在d后面追加
 * 如果nums[i]<d[len],则找到d中比nums[i]大的第一个数，替换掉，len是不变的
 * @author tengyun
 * @date 2021/2/18 9:50
 **/
public class LengthOfLIS300 {

    public static void main(String[] args) {
        LengthOfLIS300 length = new LengthOfLIS300();
        length.lengthOfLIS(new int[]{7,7,7,7,7,7});
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length, len = 1;
        int[] d = new int[n+1];
        d[1] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {
                // 该数大于最后一个则补充到后面
                d[++len] = nums[i];
            } else if (nums[i] < d[len]) {
                // 该数小于最后一个，找到第一个小于等于这个数的数，替换
                int l = 1, r = len, mid = 1;
                while (l < r) {
                    mid = l + (r-l)/2;
                    if (d[mid] == nums[i]) {
                        break;
                    } else if (d[mid] < nums[i]) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                if (d[mid] != nums[i]) {
                    if (d[l] < nums[i]) {
                        d[l+1] = nums[i];
                    } else if (d[l] > nums[i]) {
                        d[l] = nums[i];
                    }
                }
            }
        }
        return len;
    }

    public int lengthOfLIS1(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
