package com.life.algorithm.hot100;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author tengyun
 * @date 2021/2/19 20:08
 **/
public class DecodeString394 {

    public String decodeString2(String s) {
        char[] sArray = s.toCharArray();
        int length = sArray.length;
        StringBuilder res = new StringBuilder();
        Deque<String> stack = new LinkedList<>();

        for (int i = 0; i < length;) {
            char cur = sArray[i];
            if (Character.isDigit(cur)) {
                // 数字
                StringBuilder digit = new StringBuilder();
                while (i < length && Character.isDigit(sArray[i])) {
                    digit.append(sArray[i++]);
                }
                stack.addLast(digit.toString());
            } else if (Character.isLetter(cur)) {
                // 字母
                StringBuilder letter = new StringBuilder();
                while (i < length && Character.isLetter(sArray[i])) {
                    letter.append(sArray[i++]);
                }
                stack.addLast(letter.toString());
            } else if (cur == '[') {
                // '['
                stack.addLast(String.valueOf(cur));
                i++;
            } else {
                // ']'
                StringBuilder repeat = new StringBuilder();
                while (!stack.isEmpty() && !"[".equals(stack.peekLast())) {
                    repeat.insert(0, stack.pollLast());
                }
                stack.pollLast();
                int num = Integer.parseInt(stack.pollLast());
                StringBuilder letters = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    letters.append(repeat);
                }
                stack.addLast(letters.toString());
                i++;
            }
        }
        while(!stack.isEmpty()) {
            res.insert(0, stack.pollLast());
        }
        return res.toString();
    }

    public String decodeString(String s) {
        char[] sArray = s.toCharArray();
        StringBuilder res = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        for (char c : sArray) {
            if (c != ']') {
                stack.addLast(c);
            } else {
                StringBuilder repeat = new StringBuilder();
                while (!stack.isEmpty() && stack.peekLast() != '[') {
                    repeat.insert(0, stack.pollLast());
                }
                stack.pollLast();

                StringBuilder numS = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peekLast())) {
                    numS.insert(0, stack.pollLast());
                }
                int num = Integer.parseInt(numS.toString());
                StringBuilder repeatComplete = new StringBuilder();
                for (int i = 0; i < num; i++) {
                    repeatComplete.append(repeat);
                }
                if (stack.isEmpty()) {
                    res.append(repeatComplete);
                } else {
                    for (char c1 : repeatComplete.toString().toCharArray()) {
                        stack.addLast(c1);
                    }
                }
            }
        }
        StringBuilder tail = new StringBuilder();
        while (!stack.isEmpty()) {
            tail.insert(0, stack.pollLast());
        }
        return res.append(tail).toString();
    }

    public static void main(String[] args) {
        DecodeString394 decodeString394 = new DecodeString394();
        System.out.println(decodeString394.decodeString2("ty3[a2[c]]"));
    }

    public String decodeString1(String s) {
        int length = s.length();
        StringBuilder res = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        for (int i = 0; i < length;) {
            char cur = s.charAt(i);
            // 如果是数字，获取数字代表的字符串入栈
            if (Character.isDigit(cur)) {
                StringBuilder digit = new StringBuilder();
                while (i < length && Character.isDigit(s.charAt(i))) {
                    digit.append(s.charAt(i++));
                }
                stack.addLast(digit.toString());
            } else if (cur == '[') {
                // 如果不是]，入栈
                stack.addLast(String.valueOf(cur));
                i++;
            } else if (cur == ']') {
                // 开始出栈，直到遇到[
                StringBuilder repeat = new StringBuilder();
                while (!stack.isEmpty() && !"[".equals(stack.peekLast())) {
                    repeat.insert(0, stack.pollLast());
                }
                // 弹出 '['
                stack.pollLast();
                // 弹出循环次数
                StringBuilder letters = new StringBuilder();
                int num = Integer.parseInt(Objects.requireNonNull(stack.pollLast()));
                for (int j = 0; j < num; j++) {
                    letters.append(repeat);
                }
                stack.addLast(letters.toString());
                i++;
            } else {
                // 是字母
                StringBuilder letter = new StringBuilder();
                while (i < length && Character.isLetter(s.charAt(i))) {
                    letter.append(s.charAt(i++));
                }
                stack.addLast(letter.toString());
            }
        }
        while (!stack.isEmpty()) {
            res.insert(0, stack.pollLast());
        }
        return res.toString();
    }



}
