package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengyun
 * @date 2021/1/1 16:58
 **/
public class PrintTree655 {

    public static void main(String[] args) {
        PrintTree655 printTree655 = new PrintTree655();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        printTree655.printTree(root);
    }

    List<List<String>> result = new ArrayList<>();

    List<TreeNode> list = new ArrayList<>();

    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return result;
        }
        list.add(root);
        int m = 1;
        int number;
        boolean isNotAllNull = true;
        while (isNotAllNull) {
            isNotAllNull = false;
            number = (int) Math.pow(2, m-1);
            List<TreeNode> nodes = new ArrayList<>();
            while (number > 0) {
                int index = list.size() - number;
                TreeNode node = list.get(index);
                if (node == null) {
                    nodes.add(null);
                    nodes.add(null);
                } else {
                    nodes.add(node.left);
                    nodes.add(node.right);
                    if (node.left != null || node.right != null) {
                        isNotAllNull = true;
                    }
                }
                number --;
            }
            if (isNotAllNull) {
                list.addAll(nodes);
                m++;
            }
        }
        for (int i = 1; i <= m; i++) {
            List<String> stringList = new ArrayList<>();
            int nullStringNum = (int)Math.pow(2, m-i) - 1;
            for (int j = 0; j < nullStringNum; j++) {
                stringList.add("");
            }
            int nodeNum = (int)Math.pow(2, i-1);
            for (int x = 1; x <= nodeNum; x++) {
                TreeNode treeNode = list.get((int) Math.pow(2, i - 1) - 1 + x - 1);
                if (treeNode == null) {
                    stringList.add("");
                } else {
                    stringList.add(String.valueOf(treeNode.val));
                }
                int j = x < nodeNum ? 2 * nullStringNum + 1 : nullStringNum;
                for (; j > 0 ; j--) {
                    stringList.add("");
                }
            }
            result.add(stringList);
        }
        return result;
    }

}
