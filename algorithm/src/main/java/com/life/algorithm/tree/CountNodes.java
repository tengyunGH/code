package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author tengyun
 * @date 2020/12/15 20:00
 **/
public class CountNodes {

    private Map<Integer, Integer> countMap = new HashMap<>();

    private int max;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return null;
        }
        int count = count(root);
        countMap.putIfAbsent(count, countMap.getOrDefault(count, 0) + 1);
        List<Integer> list = new ArrayList<>();
        Set<Integer> keys = countMap.keySet();
        for(Integer key : keys) {
            if (countMap.get(key) == max) {
                list.add(key);
            }
        }
        return new int[10];
    }


    public int count(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int count = root.val + count(root.left) + count(root.right);
            int frequency = countMap.getOrDefault(count, 0) + 1;
            if (frequency > max) {
                max = frequency;
            }
            countMap.putIfAbsent(count, frequency);
            return count;
        }
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);
        countNodes(node1);
        System.out.println(node1.val);
    }


    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = 0;
        TreeNode node = root;
        while (node.left != null) {
            h++;
            node = node.left;
        }
        int min = 1 << h, max = (1 << (h + 1)) - 1;
        int mid;
        int result = min;
        while (min <= max) {
            mid = min + (max - min) / 2;
            if (exit(root, h, mid)) {
                result = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return result;
    }

    public static boolean exit(TreeNode root,int h, int mid) {
        int bits = 1 << (h - 1);
        while (root != null && bits > 0) {
            if ((bits & mid) == 1) {
                root = root.right;
            } else {
                root = root.left;
            }
            bits >>= 1;
        }
        return root != null;
    }
    
}
