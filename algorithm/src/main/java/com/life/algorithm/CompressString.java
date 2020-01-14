package com.life.algorithm;

import java.util.Scanner;

public class CompressString {
    public static void main(String[] args) {
        String compressed = new Scanner(System.in).nextLine();
        int last = compressed.lastIndexOf("[");
        while (last >= 0) {
            int yaxis = compressed.lastIndexOf("|");
            Integer i = Integer.parseInt(compressed.substring(last + 1, yaxis));

            String before = compressed.substring(0,last);
            int first = compressed.substring(last).indexOf("]") + before.length();
            String after = compressed.substring(first + 1, compressed.length());
            String repeat = compressed.substring(yaxis + 1, first);
            for (; i > 0; i--) {
                before += repeat;
            }
            compressed = before + after;
            last = compressed.lastIndexOf("[");
        }
        System.out.println(compressed);
    }
}
