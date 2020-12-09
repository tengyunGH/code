package com.life.algorithm.greedy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tengyun
 * @date 2020/10/29 19:08
 **/
public class PartitionLabels {

    public static void main(String[] args) {
        LinkedHashMap<Character, int[][]> map = new LinkedHashMap<>();
        char[] charts = "ababcbacadefegdehijhklij".toCharArray();
        for (int i = 0; i < charts.length; i++) {
            if (map.containsKey(charts[i])) {
                int[][] temp = map.get(charts[i]);
                temp[0][1] = i;
                map.put(charts[i], temp);
            } else {
                map.put(charts[i], new int[][] {{i, i}});
            }
        }
        List<Integer> result = new ArrayList<>();

        Iterator<Map.Entry<Character, int[][]>> it = map.entrySet().iterator();
        Map.Entry<Character, int[][]> entryFirst = it.next();
        int cnt = entryFirst.getValue()[0][1];
        int start = entryFirst.getValue()[0][0];

        while (it.hasNext()) {
            Map.Entry<Character, int[][]> entry = it.next();
            int[][] temp = entry.getValue();
            if (cnt > temp[0][0]) {
                if (cnt < temp[0][1]) {
                    cnt = temp[0][1];
                }
            } else {
                result.add(cnt - start + 1);
                start = temp[0][0];
                cnt = temp[0][1];
            }
        }
        result.add(cnt - start + 1);
        System.out.println(result.toString());
    }

    public List<Integer> partitionLabels(String S) {
        LinkedHashMap<Character, int[][]> map = new LinkedHashMap<>();
        char[] charts = S.toCharArray();
        List<Integer> result = new ArrayList<>();
        if (charts.length == 1) {
            result.add(1);
            return result;
        }
        for (int i = 0; i < charts.length; i++) {
            if (map.containsKey(charts[i])) {
                map.get(charts[i])[0][1] = i;
            } else {
                map.put(charts[i], new int[][] {{i, i}});
            }
        }

        Iterator<Map.Entry<Character, int[][]>> it = map.entrySet().iterator();
        Map.Entry<Character, int[][]> entryFirst = it.next();
        int cnt = entryFirst.getValue()[0][1];
        int start = entryFirst.getValue()[0][0];
        Map.Entry<Character, int[][]> entry;
        int[][] temp;
        while (it.hasNext()) {
            entry = it.next();
            temp = entry.getValue();
            if (cnt > temp[0][0]) {
                if (cnt < temp[0][1]) {
                    cnt = temp[0][1];
                }
            } else {
                result.add(cnt - start + 1);
                start = temp[0][0];
                cnt = temp[0][1];
            }
        }
        result.add(cnt - start + 1);
        return result;
    }

}
