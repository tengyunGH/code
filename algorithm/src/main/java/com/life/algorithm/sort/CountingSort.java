package com.life.algorithm.sort;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 计数排序
 * temp[i] = 4 表示i这个元素出现了4次
 * @author tengyun
 * @date 2020/8/11 20:36
 **/
public class CountingSort {

/*    public static void gf32(String[] args) {
        int[] a = {15, 2, 3, 18, 11,34,30};
        // 1、计算差值
        int max = a[0], min = a[0];
        for (int x : a) {
            if (x > max) {
                max = x;
            }
            if (x < min) {
                min = x;
            }
        }
        // 2、对每个元素遍历，放入相应的下标中
        int[] temp = new int[max - min + 1];
        // 初始化
        for (int i = 0; i < max - min + 1; i++) {
            temp[i] = 0;
        }
        for (int x : a) {
            temp[x - min]++;
        }
        // 对temp进行选择排序，
        for (int i = 0; i < ) {
        }

    }*/

    public static void main(String[] args) {
        frequencySort1("gfgfgfgrrrr");
    }

    public static String frequencySort(String s) {
        char[] c=s.toCharArray();
        int[] freqArray = new int[123];
        // 统计频率
        // 以字符的ASCII码为下标，频率为value， 最后一个小写字母的ASCII码是122
        for(char cc:c){
            // 整型数组会直接初始化为0
            freqArray[cc]++;
        }
        // 以频率为key，该字符组成的string为value
        Map<Integer, String> map = new HashMap<>();
        for (int cInt = 0; cInt < freqArray.length; cInt++) {
            int freq = freqArray[cInt];
            if (freq == 0) {
                continue;
            }
            char ch = (char)cInt;
            String elements = map.get(freq);
            if (elements == null) {
                elements = "";
            }
            for (int j = 0; j < freq; j++) {
                elements += ch;
            }
            map.put(freq, elements);
        }
        Integer[] keys = map.keySet().toArray(new Integer[]{});
        Arrays.sort(keys);
        String result = "";
        for (int j = keys.length - 1; j >= 0; j--) {
            result += map.get(keys[j]);
        }
        return result;
    }



    public static String frequencySort1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] c=s.toCharArray();
        // 统计频率
        for(char cc:c){
            Integer num = map.get(cc);
            if (num != null) {
                map.put(cc, ++num);
            } else {
                map.put(cc, 1);
            }
        }
        // 以频率为下标， 字符串为value,频率最大就是这个字符串全是相同的字符，这样频率最大是这个字符串的长度，所以构建的数组长度得是 c.length + 1
        String[] buckets = (String[]) Array.newInstance(String.class, c.length + 1);
        for (char key : map.keySet()) {
            int freq = map.get(key);
            String sameChar = buckets[freq];
            if (sameChar == null) {
                sameChar = "";
            }
            StringBuilder sb = new StringBuilder(sameChar);
            for (int i  = 0; i < freq; i++) {
                sb.append(key);
            }
            buckets[freq] = sb.toString();
        }
        // 从后往前拼接
        StringBuilder result = new StringBuilder("");
        for (int i = buckets.length - 1; i >= 0; i--) {
            if (buckets[i] != null) {
                result.append(buckets[i]);
            }
        }
        return result.toString();
    }

    private String build(char ch, int times) {
        String string = Character.toString(ch);
        StringBuilder res = new StringBuilder(string);
        int t = 1;
        while (t < times) {
            res.append(string);
            t++;
        }

        return res.toString();
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 找到最大最小值
        int min = nums[0];
        for (int x : nums) {
            if (x < min) {
                min = x;
            }
        }
        // 对每个元素选择相应的桶，做累计操作
        Map<Integer, Integer> buckets = new HashMap<>();
        for (int num : nums) {
            Integer bucket = buckets.get(num - min + 1);
            if (bucket != null) {
                buckets.put(num - min + 1, ++bucket);
            } else {
                buckets.put(num - min + 1, 1);
            }
        }
        // 以桶的大小（相同元素的个数）为下标，此时需要构建的数组长度得是传入数组的长度了，因为有可能这个数组的元素都是一样的
        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i <= nums.length; i++) {
            lists.add(new LinkedList<>());
        }
        for (Integer key : buckets.keySet()) {
            lists.get(buckets.get(key)).add(key);
        }
        // 找到后k个就是出现频率最高的了
        int[] result = new int[k];
        for (int i = lists.size() - 1, j = 0; i >= 0 && j < k; i--) {
            for (Integer a : lists.get(i)) {
                result[j] = a;
                j++;
            }
        }
        return result;
    }
}
