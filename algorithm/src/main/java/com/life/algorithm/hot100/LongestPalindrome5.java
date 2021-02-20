package com.life.algorithm.hot100;

/**
 * @author tengyun
 * @date 2021/2/20 16:11
 **/
public class LongestPalindrome5 {

    private int maxLen;

    private String maxStr;

    public String longestPalindrome(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 回文中心是i
            computeNum(i, i, s);
            // 回文中心是i 和 i+1
            computeNum(i, i+1, s);
        }
        return maxStr;
    }

    public void computeNum(int l, int r, String s) {
        int n = s.length();
        while (l >= 0 && r < n) {
            if (s.charAt(l) == s.charAt(r)) {
                // 如果左右相等，说明这还是个回文串
                if (r - l + 1 > maxLen) {
                    maxLen = r - l + 1;
                    maxStr = s.substring(l, r + 1);
                }
            } else {
                // 一旦有不相等的，那么说明不是回文串了
                return;
            }
            l--;
            r++;
        }
    }

}
