package com.life.algorithm.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tengyun
 * @date 2020/12/31 15:44
 **/
public class FindTarget653 {

    public static void main(String[] args) {
        FindTarget653 findTarget653 = new FindTarget653();
        findTarget653.findTarget(new TreeNode(1), 2);
    }


    private int target;

    public boolean findTarget1(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        target = k;
        return find(root);
    }

    public boolean find(TreeNode node) {
        if (node == null) {
            return false;
        }
        if (values.containsKey(target - node.val)) {
            return true;
        }
        values.put(node.val, 1);
        return find(node.left) || find(node.right);
    }

    private final Map<Integer, Integer> values = new HashMap<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        fillMap(root);
        for (Integer val : values.keySet()) {
            if (k == 2*val && values.get(val) == 2) {
                return true;
            }
            if (k != 2*val && values.containsKey(k-val)) {
                return true;
            }
        }
        return false;
    }

    public void fillMap(TreeNode node) {
        if (node == null) {
            return;
        }
        values.put(node.val, values.getOrDefault(node.val, 0)+1);
        fillMap(node.left);
        fillMap(node.right);
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int depth = 1;
        int size = deque.size();
        while (size > 0) {
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = deque.removeFirst();
                if (treeNode.left == null && treeNode.right == null) {
                    return depth;
                }
                if (treeNode.left != null) {
                    deque.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.addLast(treeNode.right);
                }
            }
            depth++;
            size = deque.size();
        }
        return depth;
    }

}
