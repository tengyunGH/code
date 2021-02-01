package com.life.algorithm.dp;

import java.util.Map;

/**
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * @author tengyun
 * @date 2021/1/21 19:24
 **/
public class LongestValidParentheses32 {

    public int longestValidParentheses(String s) {
        char[] charArray = s.toCharArray();
        int n = charArray.length;
        if (n <= 1) {
            return 0;
        }

        int max = 0, i = 0;
        while (i < n) {
            while(charArray[i] != '(' ) {
                i++;
            }
            int temp = 0;
            while(i < n-1) {
                if (charArray[i] == '(' && charArray[i+1] == ')') {
                    temp++;
                    i += 2;
                } else {
                    i++;
                    break;
                }
            }
            max = Math.max(max, temp);
        }
        return max;
    }

}
