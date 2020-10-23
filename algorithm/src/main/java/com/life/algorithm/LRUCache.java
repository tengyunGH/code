package com.life.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tengyun
 * @date 2020/9/10 19:28
 **/
public class LRUCache {

    class Node {

        private int key;

        private Node previous;

        private Node next;

        private int value;

        public Node(Node previous, Node next, int value, int key) {
            this.key = key;
            this.previous = previous;
            this.next = next;
            this.value = value;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private final int capacity;

    private Node head = null;

    private Node tail = null;

    private final HashMap<Integer, Node> map = new HashMap<>();

    private final List<Node> container = new ArrayList<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        changeToTail(node);
        return node.getValue();
    }

    public void changeToTail(Node node) {
        if (this.tail == node) {
            return;
        }
        Node next = node.getNext();
        if (this.head == node) {
            // 该节点是头结点
            this.head = next;
            next.previous = null;
        } else {
            Node pre = node.getPrevious();
            pre.next = next;
            next.previous = pre;
        }
        this.tail.next = node;
        node.previous = this.tail;
        this.tail = node;
        node.next = null;
    }

    public void put(int key, int value) {
        synchronized (this) {
            Node node = map.get(key);
            // 包含put的元素，将他的访问顺序放到队尾，并更新value
            if (null != node) {
                changeToTail(node);
                node.value = value;
            } else {
                // 不包含put的元素，分为容器满没满两种情况
                int size = container.size();
                if (size < capacity) {
                    node = new Node(this.tail, null, value, key);
                    if (this.tail != null) {
                        this.tail.next = node;
                    }
                    this.tail = node;
                    map.put(key, node);
                    if (size == 0) {
                        this.head = node;
                    }
                    container.add(node);
                } else {
                    // 需要挪出头部元素，将这个放在头部的位置，改变指针之间的关系
                    // 从map中删除这个头部元素
                    map.remove(this.head.key);
                    node = this.head;
                    node.key = key;
                    node.value = value;
                    Node nextHead = this.head.next;
                    if (nextHead != null) {
                        this.head = nextHead;
                        nextHead.previous = null;
                        this.tail.next = node;
                        node.previous = this.tail;
                        this.tail = node;
                    }
                    map.put(key, node);
                }
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(10);
        cache.put(10,13);
        cache.put(3,17);
        cache.put(6,11);
        cache.put(10,5);
        cache.put(9,10);
        cache.get(13);
        cache.put(2,19);
        cache.get(2);
        cache.get(3);
        cache.put(5,25);
        cache.get(8);
        cache.put(9,22);
        cache.put(5,5);
        cache.put(1,30);
        cache.get(11);
        cache.put(9,12);
        cache.get(7);
        cache.get(5);
        cache.get(8);
        cache.get(9);
        cache.put(4,30);
        cache.put(9,3);
        cache.get(9);
        cache.get(10);
        cache.get(10);
        cache.put(6,14);
        cache.put(3,1);
        cache.get(3);
        cache.put(10,11);
        cache.get(8);
        cache.put(2,14);
        cache.get(1);
        cache.get(5);
        cache.get(4);
        cache.put(11,4);
        cache.put(12,24);
        cache.put(5,18);
        cache.get(13);
        cache.put(7,23);
        cache.get(8);
        cache.get(12);
        cache.put(3,27);
        cache.put(2,12);
        cache.get(5);
        cache.put(2,9);
        cache.put(13,4);
        cache.put(8,18);
        cache.put(1,7);
        cache.get(6);
        cache.put(9,29);
        cache.put(8,21);
        cache.get(5);
        cache.put(6,30);
        cache.put(1,12);
        cache.get(10);
        cache.put(4,15);
        cache.put(7,22);
        cache.put(11,26);
        cache.put(8,17);
        cache.put(9,29);
        cache.get(5);
        cache.put(3,4);
        cache.put(11,30);
        cache.get(12);
        cache.put(4,29);
        cache.get(3);
        cache.get(9);
        cache.get(6);
        cache.put(3,4);
        cache.get(1);
        cache.get(10);
        cache.put(3,29);
        cache.put(10,28);
        cache.put(1,20);
        cache.put(11,13);
        cache.get(3);
        cache.put(3,12);
        cache.put(3,8);
        cache.put(10,9);
        cache.put(3,26);
        cache.get(8);
        cache.get(7);
        cache.get(5);
        cache.put(13,17);
        cache.put(2,27);
        cache.put(11,15);
        cache.get(12);
        cache.put(9,19);
        cache.put(2,15);
        cache.put(3,16);
        cache.get(1);
        cache.put(12,17);
        cache.put(9,1);
        cache.put(6,19);
        cache.get(4);
        cache.get(5);
        cache.get(5);
        cache.put(8,1);
        cache.put(11,7);
        cache.put(5,2);
        cache.put(9,28);
        cache.get(1);
        cache.put(2,2);
        cache.put(7,4);
        cache.put(4,22);
        cache.put(7,24);
        cache.put(9,26);
        cache.put(13,28);
        cache.put(11,26);
    }

}
