package com.life.algorithm.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tengyun
 * @date 2021/1/8 9:13
 **/
public class MinimumTotal120 {

    public static void main(String[] args) {
        MinimumTotal120 minimumTotal120 = new MinimumTotal120();
        List<List<Integer>> x = new ArrayList<>();
        x.add(Arrays.asList(2));
        x.add(Arrays.asList(3,4));
        x.add(Arrays.asList(6,5,7));
        x.add(Arrays.asList(4,1,8,3));
        minimumTotal120.minimumTotal(x);
    }


    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int dp[][] = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> thisFloor = triangle.get(i);
            dp[i][0] = dp[i-1][0] + thisFloor.get(0);
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + thisFloor.get(j);
            }
            dp[i][i] = dp[i-1][i-1] + thisFloor.get(i);
        }
        int max = dp[n-1][0];
        for (int i = 1; i < n; i++) {
            if (max > dp[n-1][i]) {
                max = dp[n-1][i];
            }
        }
        return max;
    }

}
