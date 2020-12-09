package com.life.algorithm.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NoOverlappingInterval {


    public static void main(String[] args) {
        // int[][] intervals = new int[][]{{3,9},{7,12},{3,8},{6,8},{9,10},{2,9},{0,9},{3,9},{0,6},{2,8}};
//        int[][] intervals = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
//        reConstructQueue(intervals);
        isSubsequence("abc", "ahbgdc");
    }

    public static int[][] reConstructQueue(int[][] peoples) {
        if (peoples.length <= 1) {
            return peoples;
        }
        Arrays.sort(peoples, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return 1;
                } else if (o1[0] == o2[0]) {
                    if (o1[1] > o2[1]) {
                        return 1;
                    } else if (o1[1] == o2[1]) {
                        return 0;
                    }
                }
                return -1;
            }
        });
        List<int[]> newPeoples = new ArrayList<>();
        for (int i = 0; i < peoples.length; i++) {
            newPeoples.add(peoples[i][1], peoples[i]);
        }
        return newPeoples.toArray(new int[peoples.length][]);
    }

    public static void set(int[][] people, int index, int[] item) {
        for (int i = people.length - 2; i >= index; i--) {
            people[i+1][0] = people[i][0];
            people[i+1][1] = people[i][1];
        }
        people[index][0] = item[0];
        people[index][1] = item[1];
    }

    public static int findMinArrowShots(int[][] points) {
        if (points.length == 1) {
            return 1;
        } else if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] == o2[1]) {
                    if (o1[0] < o2[0]) {
                        return -1;
                    } else if (o1[0] == o2[0]) {
                        return 0;
                    }
                }
                return 1;
            }
        });
        int cnt = points[0][1];
        int arrow = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= cnt) {
                continue;
            }
            arrow++;
            cnt = points[i][1];
        }
        return arrow;
    }

    public static int ea(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int cnt = 1;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                continue;
            }
            end = intervals[i][1];
            cnt++;
        }
        return intervals.length - cnt;
    }

    // 移除区间的最小数量，那么相同的起点，留下的终点越小，就越不容易和后面的冲突，需要移除的就越少
    public static int eraseOverlapIntervals(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) {
            return 0;
        }
        //  将数组先按照起点排序
        quicksort(intervals, 0, length-1);
        // 遍历数组，当有重复区间时，留下
        int[][] temp = new int[1][2];
        temp[0][0] = intervals[0][0];
        temp[0][1] = intervals[0][1];
        int result = 0;
        for (int i = 1; i < length; i++) {
            if ((temp[0][0] < intervals[i][0] && intervals[i][0] < temp[0][1])
                || (temp[0][0] == intervals[i][0] && intervals[i][1] == temp[0][1])
                || ((temp[0][0] < intervals[i][1] && intervals[i][1] < temp[0][1]))
                || (temp[0][0] > intervals[i][0] && intervals[i][1] > temp[0][0])
                || ((temp[0][1] > intervals[i][0] && intervals[i][1] > temp[0][1]))) {
                result++;
                if (temp[0][1] > intervals[i][1]) {
                    temp[0][0] = intervals[i][0];
                    temp[0][1] = intervals[i][1];
                }
            } else {
                temp[0][0] = intervals[i][0];
                temp[0][1] = intervals[i][1];
            }
        }
        return result;
    }

    // 快速排序
    public static void quicksort(int[][] intervals, int left, int right) {
        if (left >= right) {
            return;
        }
        Random random = new Random();
        // 找到随机值
        int randomInt = left + random.nextInt(right - left);
        int[][] swap = new int[1][2];
        swap[0][0] = intervals[randomInt][0];
        swap[0][1] = intervals[randomInt][1];
        intervals[randomInt][0] = intervals[left][0];
        intervals[randomInt][1] = intervals[left][1];
        intervals[left][0] = swap[0][0];
        intervals[left][1] = swap[0][1];

        int i = left, j = right;
        while (i < j) {
            while (i < j && intervals[j][0] >= intervals[left][0]) {
                j--;
            }
            while (i < j && intervals[i][0] <= intervals[left][0]) {
                i++;
            }
            if (i < j) {
                int[][] swapij = new int[1][2];
                swapij[0][0] = intervals[left][0];
                swapij[0][1] = intervals[left][1];
                intervals[left][0] = intervals[right][0];
                intervals[left][1] = intervals[right][1];
                intervals[right][0] = swapij[0][0];
                intervals[right][1] = swapij[0][1];
            }
        }
        int[][] swapij = new int[1][2];
        swapij[0][0] = intervals[i][0];
        swapij[0][1] = intervals[i][1];
        intervals[i][0] = intervals[left][0];
        intervals[i][1] = intervals[left][1];
        intervals[left][0] = swapij[0][0];
        intervals[left][1] = swapij[0][1];
        quicksort(intervals, left, i);
        quicksort(intervals, i + 1, right);
    }

    public static boolean isSubsequence(String s, String t) {
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        int i = 0, j = 0;
        for (; i < chars.length; i++) {
            for (; j < chart.length; j++) {
                if (chars[i] == chart[j]) {
                    j++;
                    break;
                }
            }
            if (j == chart.length) {
                break;
            }
        }
        if (i == chars.length) {
            return true;
        }
        return false;
    }

    public boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }
        boolean flag = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                nums[i + 1] = nums[i];
                if (flag) {
                    return false;
                } else {
                    flag = true;
                }
            }
        }
        return true;
    }

    public List<Integer> partitionLabels(String S) {
        LinkedHashMap<Character, int[][]> map = new LinkedHashMap<>();
        char[] charts = S.toCharArray();
        for (int i = 0; i < charts.length; i++) {
            if (map.containsKey(charts[i])) {
                int[][] temp = map.get(charts[i]);
                temp[0][1] = i;
                map.put(charts[i], temp);
            } else {
                map.put(charts[i], new int[][] {{i, 0}});
            }
        }
        List<Integer> result = new ArrayList<>();

        Iterator<Map.Entry<Character, int[][]>> it = map.entrySet().iterator();
        Map.Entry<Character, int[][]> entryFirst = it.next();
        int cnt = entryFirst.getValue()[0][1];
        int start = entryFirst.getValue()[0][0];

        for(Map.Entry<Character, int[][]> entry : map.entrySet()) {
            int[][] temp = entry.getValue();
            if (cnt > temp[0][0]) {
                cnt = temp[0][1];
            } else {
                result.add(cnt - start);
                start = temp[0][0];
                cnt = temp[0][1];
            }

        }
        return result;
    }

}

