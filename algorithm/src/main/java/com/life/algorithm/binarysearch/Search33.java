package com.life.algorithm.binarysearch;

/**
 * @author tengyun
 * @date 2021/2/9 17:19
 **/
public class Search33 {

    public static void main(String[] args) {
        Search33 search33 = new Search33();
        int search = search33.search(new int[]{4,5,6,7,0,1,2}, 0);
        System.out.println(search);
    }

    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length == 1) {
            if (target == nums[0]) {
                return 0;
            } else {
                return -1;
            }
        }
        int left = 0, right = length - 1, mid;
        while (left <= right) {
            mid = left + (right - left)/2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                if (nums[left] > nums[right] && nums[mid] < nums[left] && target > nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                // 目标值大于中位数
                if (nums[left] > nums[right] && nums[mid] >= nums[left] && target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
