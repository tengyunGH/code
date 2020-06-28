package com.life.algorithm.doublepointer1;

/**
 * 可以删除一个字符，判断是否能构成回文字符串
 * LeetCode 680  https://leetcode-cn.com/problems/valid-palindrome-ii/description/
 * @author tengyun
 * @version 1.0
 * @date 2020/6/22 11:10
 **/
public class VerifyPalindrome {

    public static void main(String[] args) {
        System.out.println(validPalindrome("abcdudyuhuyduddcba"));
    }

    public static boolean validPalindrome(String s) {
        return isPalindrome(s, 0);
    }

    public static boolean isPalindrome(String s, int count) {
        for (int i = 0, j = s.length() - 1; i < j;) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;j--;
            } else if (count == 1) {
                return false;
            } else {
                return isPalindrome(s.substring(i, j), 1) || isPalindrome(s.substring(i + 1, j + 1), 1);
            }
        }
        return true;
    }

}
