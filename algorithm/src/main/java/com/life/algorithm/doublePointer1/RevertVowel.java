package com.life.algorithm.doublePointer1;

/**
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * https://leetcode-cn.com/problems/reverse-vowels-of-a-string/description/   345
 * @author tengyun
 * @version 1.0
 * @date 2020/6/22 11:09
 **/
public class RevertVowel {

    public String reverseVowels(String s) {
        char[] charArray = s.toCharArray();
        char temp;
        for (int i = 0, j = charArray.length - 1; i < j;) {
            if (isVowel(charArray[i]) && isVowel(charArray[j])) {
                temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
                i++;j--;
            } else if (isVowel(charArray[i]) && !isVowel(charArray[j])) {
                j--;
            } else if (!isVowel(charArray[i]) && isVowel(charArray[j])) {
                i++;
            } else {
                i++;j--;
            }
        }
        return String.valueOf(charArray);
    }

    public boolean isVowel(char a) {
        switch (a) {
            case 'a':
            case 'o':
            case 'e':
            case 'i':
            case 'u':
            case 'A':
            case 'O':
            case 'E':
            case 'I':
            case 'U': return true;
            default : return false;
        }
    }

}
