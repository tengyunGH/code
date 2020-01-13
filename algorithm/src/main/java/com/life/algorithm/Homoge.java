package com.life.algorithm;

import java.util.Arrays;
import java.util.Scanner;

/*
编码实现一个命令行工具，判定两个指定的字符串是否异构同质；异构同质的定义为：一个字符串的字符重新排列后，能变成另一个字符串。
 */
public class Homoge {

    public static void main(String[] args) {
        String[] strings = new Scanner(System.in).nextLine().split(" ");
        char[] one = strings[0].toCharArray();
        char[] two = strings[1].toCharArray();
        Arrays.sort(one);
        Arrays.sort(two);
        if (one.length == two.length) {
            for (int i = 0; i < one.length; i++) {
                if (!(one[i] == two[i])) {
                    System.out.println("false");
                    return;
                }
            }
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
