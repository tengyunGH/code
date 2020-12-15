package com.life.algorithm.tree;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author tengyun
 * @date 2020/9/28 21:11
 **/
public class RBTree {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            StringBuilder builder = new StringBuilder("");
            char[] charArray = scanner.nextLine().toCharArray();
            if (charArray.length < 3) {
                continue;
            }
            int index = 0;
            while (index < charArray.length) {
                if (charArray[index] == charArray[index+1]) {

                }
            }
            System.out.println(builder.toString());
        }
    }

}
