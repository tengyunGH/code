package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/16 11:31
 **/
public class Symmetric {

    public static void main(String[] args) {

    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        boolean flag;
        while (nodes.size() > 0) {
            List<TreeNode> nextFloor = new ArrayList<>();
            flag = true;
            for (TreeNode node : nodes) {
                if (node == null) {
                    nextFloor.add(null);
                    nextFloor.add(null);
                } else {
                    nextFloor.add(node.left);
                    nextFloor.add(node.right);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
            int size = nextFloor.size() - 1;
            TreeNode left,right;
            for (int i = 0; i <= size/2;i++) {
                left = nextFloor.get(i);
                right = nextFloor.get(size-i);
                if ((left == null && right == null) ||
                    (left != null && right != null && left.val == right.val)) {
                    continue;
                } else {
                    return false;
                }
            }
            nodes = nextFloor;
        }
        return true;
    }

}
