package com.life.algorithm;

import java.util.*;

public class LongestSubString {

    public static void main(String[] args) {
        String[] strings = new Scanner(System.in).nextLine().split(" ");
        String one = strings[0];
        String two = strings[1];
        List<String> result = new ArrayList<>();
        String longer = one.length() >= two.length() ? one : two;
        String shorter = one.length() < two.length() ? one : two;
        for (int i = shorter.length(); i > 0; i--) {
            for (int j = 0; (j + i) <= shorter.length(); j++) {
                String subString = shorter.substring(j, j + i);
                if (longer.contains(subString)) {
                    result.add(subString);
                }
            }
            if (result.size() > 0) {
                break;
            }
        }
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.compareTo(o2) >= 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        for (String string : result) {
            System.out.println(string);
        }
    }

}
