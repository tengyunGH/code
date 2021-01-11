package com.life.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tengyun
 * @date 2021/1/11 9:15
 **/
public class Rob337 {


    /***************************方法一 递归调用 ********
     * 当前节点偷，四个孙子节点的值+当前节点值
     * 当前节点不偷，两个孩子节点的值
     *************************************************************/
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int value1 =  0;
        int value2 = root.val;
        if (root.left != null) {
            value1 += rob(root.left);
            if (root.left.left != null) {
                value2 += rob(root.left.left);
            }
            if (root.left.right != null) {
                value2 += rob(root.left.right);
            }
        }
        if (root.right != null) {
            value1 += rob(root.right);
            if (root.right.left != null) {
                value2 += rob(root.right.left);
            }
            if (root.right.right != null) {
                value2 += rob(root.right.right);
            }
        }
        return Math.max(value1, value2);
    }

    /****************************
     * 记忆化搜索
     * 在方法一的基础上加上记忆化
     * 计算孩子节点的孩子节点时候，其实也是计算当前节点的孙子节点，所以是有重复的
     * ***********************************************/

    private final Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Integer val = memo.get(root);
        if (val != null) {
            return memo.get(root);
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int value1 =  0;
        int value2 = root.val;
        if (root.left != null) {
            value1 += rob(root.left);
            if (root.left.left != null) {
                value2 += rob(root.left.left);
            }
            if (root.left.right != null) {
                value2 += rob(root.left.right);
            }
        }
        if (root.right != null) {
            value1 += rob(root.right);
            if (root.right.left != null) {
                value2 += rob(root.right.left);
            }
            if (root.right.right != null) {
                value2 += rob(root.right.right);
            }
        }
        int max = Math.max(value1, value2);
        memo.put(root, max);
        return max;
    }

    /*******************
     * 方法三
     * 一个节点会有两种情况，选要不要选自己，
     * 不选自己 则左右子节点的两种情况的最大值相加
     * 选自己，则左右子节点选取不选自己的情况加上本节点值
     * 所以是dfs
     ***********************************************/
    public int rob3(TreeNode root) {
        int[] dfs = dfs(root);
        return Math.max(dfs[0], dfs[1]);
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int notSelected = Math.max(l[1], l[0]) + Math.max(r[1], r[0]);
        int selected = l[0] + r[0] + root.val;
        return new int[]{notSelected, selected};
    }

}
