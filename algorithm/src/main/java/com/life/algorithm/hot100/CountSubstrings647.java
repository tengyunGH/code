package com.life.algorithm.hot100;

/**
 * @author tengyun
 * @date 2021/2/20 14:37
 **/
public class CountSubstrings647 {

    public static void main(String[] args) {
        CountSubstrings647 countSubstrings647 = new CountSubstrings647();
        countSubstrings647.countSubstrings("abccccba");
    }

    private int count;

    public int countSubstrings(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 遍历每个回文中心，回文中心由当前字符，或者是当前字符和下个字符组成的。
            computeNum(i, i, s);
            computeNum(i, i + 1, s);
        }
        return count;
    }

    public void computeNum(int left, int right, String s) {
        int n = s.length();
        while (left >= 0 && right < n) {
            if (s.charAt(left) == s.charAt(right)) {
                // 找到一个
                count++;
                left--;
                right++;
            } else {
                break;
            }
        }
    }

}
