package com.life.algorithm.tree;

import java.util.Stack;

/**
 * @author tengyun
 * @date 2020/12/30 8:33
 **/
public class AddOneRow623 {

    /***********************方法二 深度优先搜索，用栈迭代，O(N) O(N)*********************************/

    private static class Node {
        private TreeNode node;
        private int depth;

        public Node(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }

        public TreeNode getNode() {
            return node;
        }

        public void setNode(TreeNode node) {
            this.node = node;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
    }

    public TreeNode addOneRow2(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(new Node(root, 1));
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            TreeNode treeNode = node.getNode();
            if (treeNode != null) {
                if (node.depth == d - 1) {
                    TreeNode left = new TreeNode(v);
                    TreeNode right = new TreeNode(v);
                    left.left = treeNode.left;
                    right.right = treeNode.right;
                    treeNode.left = left;
                    treeNode.right = right;
                } else {
                    stack.push(new Node(treeNode.left, node.depth+1));
                    stack.push(new Node(treeNode.right, node.depth+1));
                }
            }
        }
        return root;
    }




















    /***********************方法一 深度优先搜索，递归，O(N) O(N)*********************************/

    private int depth;
    private int val;

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (root == null) {
            return null;
        }
        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }
        depth = d;
        val = v;
        findDepth(root, 1);
        return root;
    }

    private void findDepth(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (level == depth-1) {
            TreeNode left = new TreeNode(val);
            TreeNode right = new TreeNode(val);
            left.left = node.left;
            right.right = node.right;
            node.left = left;
            node.right = right;
            return;
        }
        if (level < depth - 1) {
            findDepth(node.left, level + 1);
            findDepth(node.right, level + 1);
        }
    }
}
