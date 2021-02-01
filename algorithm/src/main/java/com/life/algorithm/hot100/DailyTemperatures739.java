package com.life.algorithm.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/1/29 19:49
 **/
public class DailyTemperatures739 {

    public int[] dailyTemperatures1(int[] T) {
        int n = T.length;
        int[] length = new int[n];
        if (n == 1) {
            return length;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (T[j] > T[i]) {
                    length[i] = j - i;
                    break;
                }
            }
        }
        return length;
    }

    public int[] dailyTemperatures2(int[] T) {

    }

}
