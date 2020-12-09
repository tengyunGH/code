package com.life.algorithm.array;

/**
 * @author tengyun
 * @date 2020/12/7 18:57
 **/
public class RotateArary {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        rotate(nums, 2);
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0, start = 0, current, prev = nums[0], temp;
        for (; count < nums.length; start++) {
            current = start;
            do {
                current = (current + k) % nums.length;
                temp = nums[current];
                nums[current] = prev;
                prev = temp;
                count++;
            } while (current != start);
        }
    }
}
