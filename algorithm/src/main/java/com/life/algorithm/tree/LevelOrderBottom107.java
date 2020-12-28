package com.life.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/28 8:42
 **/
public class LevelOrderBottom107 {

    /***************************方法1***************************/
    List<List<Integer>> result1 = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return result1;
        }
        find2(root, 0);
        Collections.reverse(result1);
        return result1;
    }

    public void find2(TreeNode node, int level) {
        if (node == null) {
            return;
        }
        int size = result1.size();
        List<Integer> thisLevel;
        if (level >= size) {
            thisLevel = new ArrayList<>();
            result1.add(thisLevel);
        } else {
            thisLevel = result1.get(level);
        }
        thisLevel.add(node.val);
        find2(node.left, level + 1);
        find2(node.right, level + 1);
    }


    /***************************方法2*****超过17。5%**********************/

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        Deque<TreeNode> oneQueue = new ArrayDeque<>();
        queue.add(root);
        numbers.add(1);
        oneQueue.add(root);
        int size = 1;
        while(true) {
            int number = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = oneQueue.remove();
                if (node.left != null) {
                    oneQueue.addLast(node.left);
                    queue.addLast(node.left);
                    number++;
                }
                if (node.right != null) {
                    oneQueue.addLast(node.right);
                    queue.addLast(node.right);
                    number++;
                }
            }
            if (number == 0) {
                break;
            }
            size = number;
            numbers.add(number);
        }
        for (int j = numbers.size()-1; j >= 0; j--) {
            int number = numbers.get(j);
            Integer[] oneFloor = new Integer[number];
            for (int i = number - 1; i >= 0; i--) {
                TreeNode last = queue.pollLast();
                oneFloor[i] = last.val;
            }
            result.add(Arrays.asList(oneFloor));
        }
        return result;
    }

}
