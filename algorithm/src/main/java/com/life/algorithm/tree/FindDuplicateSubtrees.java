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

    private Map<String, Integer> childTreeMap = new HashMap<>();
    private List<Node> repeatNode = new ArrayList<>();

    public List<Node> findDuplicateSubtrees(Node root) {
        treeSerialize(root);
        return repeatNode;
    }

    public String treeSerialize(Node root) {
        if (root == null) {
            return "#";
        }
        // 只能是前序或后序，中序序列化的话，可能会重复
        String tree = treeSerialize(root.left) + "," + treeSerialize(root.right) + "," + root.val;
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
    private Map<String, Integer> treeId = new HashMap<>();
    private Map<Integer, Integer> idNumber = new HashMap<>();
    int t = 1;

    public List<Node> findDuplicateSubtreesTwo(Node root) {
        treeId(root);
        return repeatNode;
    }

    public int treeId(Node root) {
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
}
