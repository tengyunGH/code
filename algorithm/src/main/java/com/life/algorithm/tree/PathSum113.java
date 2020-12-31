package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/31 19:21
 **/
public class PathSum113 {
    private int sum;

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        if (root == null) {
            return result;
        }
        List<Integer> list = new ArrayList<>();
        this.sum = sum;
        fillResult(root, 0, list);
        return result;
    }

    public void fillResult(TreeNode node, int sumTemp, List<Integer> list) {
        if (node == null) {
            return;
        }
        sumTemp += node.val;
        List<Integer> thisList = new ArrayList<>(list);
        thisList.add(node.val);
        if (node.left == null && node.right == null) {
            if (sumTemp == sum) {
                result.add(thisList);
            }
            return;
        }
        fillResult(node.left, sumTemp, thisList);
        fillResult(node.right, sumTemp, thisList);
    }
}
