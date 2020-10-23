package com.life.algorithm.tree;

/**
 * 二叉搜索树
 * @author tengyun
 * @date 2020/9/28 11:08
 **/
public class BSTree<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        Node newNode = new Node(e);
        if (root == null) {
            root = newNode;
        } else {
            Node parent = root;
            Node child;
            do {
                if (parent.e.compareTo(e) > 0) {
                    child = parent.left;
                } else if (parent.e.compareTo(e) < 0) {
                    child = parent.right;
                } else {
                    break;
                }
            } while (child != null);

        }
        size++;
    }

    public void addRecursion(E e) {

    }
}
