package com.life.algorithm.tree;

/**
 * @author tengyun
 * @date 2020/12/27 14:52
 **/
public class Node<E extends Comparable<E>> {

    public E e;
    public Node<E> left, right;

    public Node(E e) {
        this.e = e;
        left = null;
        right = null;
    }

    public Node(E e, Node<E> left, Node<E> right) {
        this.e = e;
        this.left = left;
        this.right = right;
    }
}
