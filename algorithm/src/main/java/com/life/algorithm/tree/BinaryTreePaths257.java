package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/28 19:34
 **/
public class BinaryTreePaths257 {

    List<String> result = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return result;
        }
        find(root, "");
        return result;
    }

    public void find(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        StringBuilder pathSB = new StringBuilder(path).append(root.val);
        if (root.left == null && root.right == null) {
            result.add(pathSB.toString());
            return;
        }
        // 注意，使用append之后，pathSB本身就append了，而不再是原来的那个了
        String s = pathSB.append("->").toString();
        if (root.left != null) {
            find(root.left, s);
        }
        if (root.right != null) {
            find(root.right, s);
        }
    }

}
