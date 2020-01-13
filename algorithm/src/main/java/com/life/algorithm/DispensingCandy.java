package com.life.algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 链接：https://www.nowcoder.com/questionTerminal/f5279b2047a24c7d97ff6e73bebde3b7
 * 来源：牛客网
 *
 * n 个小朋友坐在一排，每个小朋友拥有 ai 个糖果，现在你要在他们之间转移糖果，使得最后所有小朋友拥有的糖果数都相同，每一次，你只能从一个小朋友身上拿走恰好两个糖果到另一个小朋友上，问最少需要移动多少次可以平分糖果，如果方案不存在输出 -1。
 */
public class DispensingCandy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.valueOf(scanner.nextLine());
        String[] candyString = scanner.nextLine().split(" ");
        Arrays.sort(candyString);
        List<Integer> candyInteger = new ArrayList<>();
        Integer candySum = 0;
        for (String num : candyString) {
            Integer candy = Integer.valueOf(num);
            candyInteger.add(candy);
            candySum += candy;
        }
        if ((candySum % n) != 0) {
            System.out.println("-1");
            return;
        }
        int average = candySum / n;
        int dvalue = 0;
        for (Integer num : candyInteger) {
            if ((Math.abs(num - average) % 2) != 0) {
                System.out.println("-1");
                return;
            }
            if (num < average) {
                dvalue += average - num;
            }
        }
        System.out.println(dvalue / 2);
    }

}
