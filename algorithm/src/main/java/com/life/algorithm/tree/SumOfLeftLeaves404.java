package com.life.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author tengyun
 * @date 2021/1/1 15:11
 **/
public class SumOfLeftLeaves404 {

    /***************方法一 bfs*****************************/
    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        bfs(root);
        return sum;
    }

    public void bfs(TreeNode node) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(node);
        int size = queue.size();
        while (size > 0) {
            TreeNode treeNode;
            List<TreeNode> list = new ArrayList<>();
            while (size > 0) {
                treeNode = queue.pollFirst();
                if (treeNode.left != null) {
                    queue.addLast(treeNode.left);
                    if (treeNode.left.left == null && treeNode.left.right == null) {
                        sum += treeNode.left.val;
                    }
                }
                if (treeNode.right != null) {
                    queue.addLast(treeNode.right);
                }
                size--;
            }
            size = queue.size();
        }
    }

    /***************方法二 dfs*****************************/

    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root.left, true);
        dfs(root.right, false);
        return sum;
    }

    public void dfs(TreeNode node, boolean isLeft) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && isLeft) {
            sum += node.val;
        }
        dfs(node.left, true);
        dfs(node.right, false);
    }
}
