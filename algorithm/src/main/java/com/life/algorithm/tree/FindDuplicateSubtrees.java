package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tengyun
 * @date 2020/12/10 20:49
 **/
public class FindDuplicateSubtrees {

    public static void main(String[] args) {
        Node node8 = new Node(222);
        Node node9 = new Node(222);
        Node node10 = new Node(222);
        Node node11 = new Node(222);
        Node node12 = new Node(222);
        Node node13 = new Node(23);
        Node node14 = new Node(23);
        Node node15 = new Node(23);
        Node node7 = new Node(23, node14, node15);
        Node node6 = new Node(23, node12, node13);
        Node node5 = new Node(23, node10, node11);
        Node node4 = new Node(23, node8, node9);
        Node node3 = new Node(23, node6, node7);
        Node node2 = new Node(23, node4, node5);
        Node node1 = new Node(23, node2, node3);
        findDuplicateSubtreesTwo(node1);
        System.out.println(count);
    }
    
    private static Map<String, Integer> childTreeMap = new HashMap<>();
    private static List<Node> repeatNode = new ArrayList<>();

    public static List<Node> findDuplicateSubtrees(Node root) {
        treeSerialize(root);
        System.out.println(t);
        return repeatNode;
    }

    public static String treeSerialize(Node root) {
        t++;
        if (root == null) {
            return "#";
        }
        // 只能是前序或后序，中序序列化的话，可能会重复
        String tree = new StringBuilder(treeSerialize(root.left)).append(",").append(treeSerialize(root.right)).append(",").append(root.e).toString();
        Integer count = childTreeMap.get(tree);
        if (count == null) {
            childTreeMap.put(tree, 1);
        } else if (count == 1) {
            repeatNode.add(root);
            childTreeMap.put(tree, 2);
        }
        return tree;
    }


    /**
     * 第二种解法
     * @author tengyun
     * @date 20:52 2020/12/10
     **/
    private static Map<String, Integer> treeId = new HashMap<>();
    private static Map<Integer, Integer> idNumber = new HashMap<>();
    static int t = 1;
    static int count = 1;

    public static List<Node> findDuplicateSubtreesTwo(Node root) {
        treeId(root);
        return repeatNode;
    }

    public static int treeId(Node root) {
        count++;
        if (root == null) {
            return 0;
        }
        String serial = root.e + "," + treeId(root.left) + "," + treeId(root.right);
        int id = treeId.computeIfAbsent(serial, x -> t++);
        idNumber.put(id, idNumber.getOrDefault(id, 0) + 1);
        if (idNumber.get(id) == 2) {
            repeatNode.add(root);
        }
        return id;
    }

}
