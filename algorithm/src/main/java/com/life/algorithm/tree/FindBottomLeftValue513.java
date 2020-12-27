package com.life.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/26 17:26
 **/
public class FindBottomLeftValue513 {


    /*****************************************解法1 BFS**********************************/

    public int findBottomLeftValue1(TreeNode root) {
        List<TreeNode> floor = new ArrayList<>();
        floor.add(root);
        int bottomLeftValue = root.val;
        while(floor.size() > 0) {
            bottomLeftValue = floor.get(0).val;
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : floor) {
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            floor = next;
        }
        return bottomLeftValue;
    }




    /*****************************************解法2  DFS**********************************/

    private int bottomLeftPos = Integer.MAX_VALUE;
    private int bottomLeftValue;
    private int levelMax = 1;

    public int findBottomLeftValue2(TreeNode root) {
        bottomLeftValue = root.val;
        find(root.left, 1, 2);
        find(root.right, 2, 2);
        return bottomLeftValue;
    }

    public void find(TreeNode root, int pos, int level) {
        if (root == null) {
            return;
        }
        // 叶子结点
        if (root.left == null && root.right == null) {
            // 更高一层，则直接更新结果
            if (level > levelMax) {
                levelMax = level;
                bottomLeftPos = pos;
                bottomLeftValue = root.val;
            } else if (level == levelMax) { // 相同的一层，则比较位置，位置小则更新
                if (pos < bottomLeftPos) {
                    bottomLeftPos = pos;
                    bottomLeftValue = root.val;
                }
            }
        }
        if (root.left != null) {
            find(root.left, 2 * pos + 1, level+1);
        }
        if (root.right != null) {
            find(root.right, 2 * pos + 2, level+1);
        }
    }

}
