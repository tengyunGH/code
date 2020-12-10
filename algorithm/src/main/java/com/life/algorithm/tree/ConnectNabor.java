package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
    @author tengyun
    @date 2020/12/8 9:00
   */
public class ConnectNabor {

    private static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node left, Node right, Node next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }

    public static void main(String[] args) {
        Node node8 = new Node(222);
        Node node9 = new Node(222);
        Node node10 = new Node(222);
        Node node11 = new Node(222);
        Node node12 = new Node(222);
        Node node13 = new Node(23);
        Node node14 = new Node(23);
        Node node15 = new Node(23);
        Node node7 = new Node(23, node14, node15, null);
        Node node6 = new Node(23, node12, node13, null);
        Node node5 = new Node(23, node10, node11, null);
        Node node4 = new Node(23, node8, node9, null);
        Node node3 = new Node(23, node6, node7, null);
        Node node2 = new Node(23, node4, node5, null);
        Node node1 = new Node(23, node2, node3, null);
        findDuplicateSubtrees(node1);
        System.out.println("789");
    }

    // 辅助函数
    public static void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        /**** 前序遍历位置   ***/
        // 将传入的两个节点连接
        node1.next = node2;

        // 连接相同父节点的两个子节点
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        // 连接跨越父节点的两个子节点
        connectTwoNode(node1.right, node2.left);
    }

    private static Map<String, Integer> treeId = new HashMap<>();
    private static Map<Integer, Integer> idNumber = new HashMap<>();
    private static List<Node> repeatNode = new ArrayList<>();
    static int t = 1;

    public static List<Node> findDuplicateSubtrees(Node root) {
        treeSerialize(root);
        return repeatNode;
    }

    public static int treeSerialize(Node root) {
        if (root == null) {
            return 0;
        }
        String serial = root.val + "," + treeSerialize(root.left) + "," + treeSerialize(root.right);
        int id = treeId.computeIfAbsent(serial, x -> t++);
        idNumber.put(id, idNumber.getOrDefault(id, 0) + 1);
        if (idNumber.get(id) == 2) {
            repeatNode.add(root);
        }
        return id;
    }
}
