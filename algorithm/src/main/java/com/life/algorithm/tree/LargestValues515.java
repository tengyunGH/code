package com.life.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/27 14:51
 **/
public class LargestValues515 {


    List<Integer> result = new ArrayList<>();

    public List<Integer> largestValues1(TreeNode root) {
        if (root == null) {
            return result;
        }
        find(root, 0);
        return result;
    }

    public void find(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level >= result.size()) {
            result.add(node.val);
        } else if (result.get(level) < node.val) {
            result.set(level, node.val);
        }
        find(node.left, level+1);
        find(node.right, level+1);
    }



    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.addLast(root);
        while (nodes.size() > 0) {
            int size = nodes.size();
            int max = Integer.MIN_VALUE;
            while (size > 0) {
                TreeNode node = nodes.remove();
                if (node.val > max) {
                    max = node.val;
                }
                if (node.left != null) {
                    nodes.addLast(node.left);
                }
                if (node.right != null) {
                    nodes.addLast(node.right);
                }
                size--;
            }
            result.add(max);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> nodes = new ArrayDeque<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            int size = nodes.size();
            List<Integer> floor = new ArrayList<>();
            while (size > 0) {
                TreeNode node = nodes.remove();
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.addLast(node.right);
                }
                floor.add(node.val);
                size--;
            }
            result.add(floor);
            size = nodes.size();
            if (size > 0) {
                List<Integer> next = new ArrayList<>();
                while (size > 0) {
                    TreeNode node = nodes.pollLast();
                    if (node.right != null) {
                        nodes.addFirst(node.right);
                    }
                    if (node.left != null) {
                        nodes.addFirst(node.left);
                    }
                    next.add(node.val);
                    size--;
                }
                result.add(next);
            }
        }
        return result;
    }



}
