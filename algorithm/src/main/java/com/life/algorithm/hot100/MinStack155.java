package com.life.algorithm.hot100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tengyun
 * @date 2021/2/20 13:37
 **/
public class MinStack155 {
    private final Deque<Integer> stack;
    private final Deque<Integer> min;
    private Integer minCur;

    /** initialize your data structure here. */
    public MinStack155() {
        stack = new LinkedList<>();
        min = new LinkedList<>();
    }

    public void push(int x) {
        stack.addLast(x);
        if (minCur == null || minCur > x) {
            minCur = x;
        }
        min.addLast(minCur);
    }

    public void pop() {
        stack.pollLast();
        min.pollLast();
        minCur = min.peekLast();
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peekLast();
        }
        return 0;
    }

    public int getMin() {
        return minCur;
    }
}

class MinStack1 {
    private static class Node {
        private int value;
        private int min;
        Node(int value, int min) {
            this.min = min;
            this.value = value;
        }
        public int getValue() {
            return value;
        }
        public int getMin () {
            return min;
        }
    }

    private final Deque<Node> stack;
    private Integer minCur;

    /** initialize your data structure here. */
    public MinStack1() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        if (minCur == null || minCur > x) {
            minCur = x;
        }
        stack.addLast(new Node(x, minCur));
    }

    public void pop() {
        stack.pollLast();
        if (!stack.isEmpty()) {
            minCur = stack.peekLast().getMin();
        } else {
            minCur = null;
        }
    }

    public int top() {
        if (!stack.isEmpty()) {
            return stack.peekLast().getValue();
        }
        return 0;
    }

    public int getMin() {
        return minCur;
    }
}
