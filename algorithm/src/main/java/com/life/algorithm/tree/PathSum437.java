package com.life.algorithm.tree;

/**
 * @author tengyun
 * @date 2020/12/16 19:40
 **/
public class PathSum437 {
    private static int count = 0;
    private static int total;

    public static void main(String[] args) {
//        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(-3, null, null);
        TreeNode node1 = new TreeNode(-2, null, node2);
        int pathSum = pathSum(node1, -5);
        System.out.println(pathSum);
    }

    public static int pathSum(TreeNode root, int sum) {
        total = sum;
        traverse(root);
        return count;
    }

    public static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        sum(root, 0);
        if (root.left != null) {
            traverse(root.left);
        }
        if (root.right != null) {
            traverse(root.right);
        }
    }

    public static void sum(TreeNode root, int culmulation) {
        if (root == null) {
            return;
        }
        culmulation += root.val;
        if (culmulation == total) {
            count++;
            return;
        }
        if ((total > 0 && culmulation > total) || (total < 0 && culmulation < total)) {
            return;
        }
        sum(root.left, culmulation);
        sum(root.right, culmulation);
    }

}
