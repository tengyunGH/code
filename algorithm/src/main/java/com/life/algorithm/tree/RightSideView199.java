package com.life.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author tengyun
 * @date 2021/1/1 16:31
 **/
public class RightSideView199 {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        int size = deque.size();
        while (size > 0) {
            TreeNode treeNode = deque.peekLast();
            result.add(treeNode.val);
            while (size > 0) {
                treeNode = deque.remove();
                if (treeNode.left != null) {
                    deque.addLast(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.addLast(treeNode.right);
                }
                size--;
            }
            size = deque.size();
        }
        return result;
    }

}
