package com.life.algorithm.binarysearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找中，m = l + (r - l) / 2; 这样当l < r的条件下，m是可能等于l，但是不会等于r，所以在while中，l的赋值不能是m，可能会造成死循环的。
 * 注意边界值的判断
 * 可以定义一个result作为容器放置可能是我们要找的目标值
 * @author tengyun
 * @date 2020/11/2 20:03
 **/
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {2,2};
        searchRange(nums, 2);
    }

    public int mySqrt(int x) {
        if (x < 4) {
            return 1;
        }
        int l =1, n = x/2;
        int m, temp;
        while(l < n) {
            m = l + (n - l) / 2;
            temp = m * m;
            if (temp == x) {
                return m;
            } else if (temp < x) {
                l = m;
            } else {
                n = m;
            }
        }
        return 0;
    }

    public static int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        int m = 0;
        while (l < r) {
            m = l + (r - l) / 2;
            if (nums[m] == nums[m + 1] && m % 2 == 0) {
                l = m + 2;
            } else if (nums[m] == nums[m + 1] && m % 2 == 1) {
                r = m - 1;
            } else if (nums[m] == nums[m - 1] && m % 2 == 0) {
                r = m - 2;
            } else if (nums[m] == nums[m - 1] && m % 2 == 1) {
                l = m + 1;
            } else {
                return nums[m];
            }
        }
        return nums[l];
    }

    public static int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0) {
            return new int[]{-1, -1};
        }
        if (length == 1) {
            if (nums[0] == target) {
                return new int[]{0,0};
            }
            return new int[]{-1,-1};
        }
        int l = 0, r =  length - 1;
        int m = 0, leftPosition = 0;
        while (l < r) {
            m = l + (r - l) / 2;
            if (nums[m] >= target) {
                leftPosition = m;
                r = m;
            } else {
                leftPosition = m + 1;
                l = m + 1;
            }
        }
        if (nums[leftPosition] == target) {
            for (int i = leftPosition + 1; i < length; i++) {
                if (nums[i] != target) {
                    return new int[]{leftPosition,i - 1};
                }
            }
        }
        return new int[]{-1,-1};
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        if (!input.contains("+") && !input.contains("-") && !input.contains("*") && input.length() > 0) {
            result.add(Integer.valueOf(input));
            return result;
        }
        /*int num = 0;
        while (index < input.length() && !isOperator(input.charAt(index))) {
            num = num * 10 + input.charAt(index) - '0';
            index++;
        }
        //将全数字的情况直接返回
        if (index == input.length()) {
            result.add(num);
            return result;
        }*/
        for (int i = 0; i < input.length(); i++) {
            if (isOperator(input.charAt(i))) {
                List<Integer> resultLeft = diffWaysToCompute(input.substring(0,i));
                List<Integer> resultRight = diffWaysToCompute(input.substring(i + 1));
                result.addAll(getList(resultLeft, resultRight, input.charAt(i)));
            }
        }
        return result;
    }

    public List<Integer> getList(List<Integer> resultLeft, List<Integer> resultRight, char operator) {
        List<Integer> result = new ArrayList<>();
        for (Integer i : resultLeft) {
            for (Integer j : resultRight) {
                int temp = 0;
                if (operator == '+') {
                    temp = i + j;
                }
                if (operator == '-') {
                    temp = i - j;
                }
                if (operator == '*') {
                    temp = i * j;
                }
                result.add(temp);
            }
        }
        return result;
    }

    boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*') {
            return true;
        }
        return false;
    }

}
