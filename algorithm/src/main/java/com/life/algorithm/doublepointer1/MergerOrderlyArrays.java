package com.life.algorithm.doublepointer1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author tengyun
 * @version 1.0
 * @date 2020/6/22 19:34
 **/
public class MergerOrderlyArrays {

    public static void main(String[] args) {
        merge(new int[]{2,0}, 1, new int[]{1}, 1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n -1;
        while (j >= 0) {
            if (i >0 && nums1[i] >= nums2[j]) {
                nums1[k] = nums1[i];
                i--;k--;
            } else {
                nums1[k] = nums2[j];
                j--;k--;
            }
        }
    }


    public String findLongestWord(String s, List<String> d) {
        int i, j;
        List<String> standard = new ArrayList<>();
        for (String target : d) {
            i = 0; j = 0;
            while (i < s.length() && j < target.length()) {
                if (s.charAt(i) == target.charAt(j)) {
                    i++;j++;
                } else {
                    i++;
                }
            }
            if (j == target.length()) {
                standard.add(target);
            }
        }
        standard.sort((o1, o2) -> {
            if (o1.compareTo(o2) < 0) {
                return -1;
            }
            return 1;
        });
        return standard.get(0);
    }

}
