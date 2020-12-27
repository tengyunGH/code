package com.life.algorithm.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tengyun                                                                                                                            ,mmnnnnnnnnnnnn
 * @date 2020/12/23 8:22
 **/
public class WidthOfBinaryTree {

    private int width = 0;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> nodes = new LinkedList<>();
        root.val = 0;
        nodes.add(root);
        int size = nodes.size();
        while (size > 0) {
            width = Math.max(width, nodes.getLast().val - nodes.getFirst().val + 1);
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                if (node.left != null) {
                    node.left.val = node.val * 2 + 1;
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    node.right.val = node.val * 2 + 2;
                    nodes.add(node.right);
                }
            }
            size = nodes.size();
        }
        return width;
    }

}
