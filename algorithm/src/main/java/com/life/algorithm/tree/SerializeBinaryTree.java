package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/14 19:34
 **/
public class SerializeBinaryTree {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        // System.out.println(serialize1(node));
        deserialize1(serialize1(node));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder("[");
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        while (true) {
            boolean flag = false;
            StringBuilder stringBuilder1 = new StringBuilder();
            List<TreeNode> nextList = new ArrayList<>();
            for (TreeNode node : nodeList) {
                if (node == null) {
                    stringBuilder1.append("null,");
                } else {
                    flag = true;
                    stringBuilder1.append(node.val).append(",");
                    nextList.add(node.left);
                    nextList.add(node.right);
                }
            }
            if (flag) {
                stringBuilder.append(stringBuilder1);
                nodeList = nextList;
            } else {
                break;
            }
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1).concat("]");
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data.length() <= 2) {
            return null;
        }
        data = data.substring(0, data.length() - 1);
        String[] nodeArray = data.split(",");
        List<TreeNode> nodeList = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.parseInt(nodeArray[0]));
        nodeList.add(root);
        for (int i = 1; i < nodeArray.length; ) {
            List<TreeNode> nodes = new ArrayList<>();
            for (int j = 0; j < nodeList.size() && i < nodeArray.length; j++) {
                TreeNode node = nodeList.get(j);
                if (!"#".equals(nodeArray[i])) {
                    node.left = new TreeNode(Integer.parseInt(nodeArray[i]));
                    nodes.add(node.left);
                }
                i++;
                if (i >= nodeArray.length) {
                    break;
                }
                if (!"null".equals(nodeArray[i])) {
                    node.right = new TreeNode(Integer.parseInt(nodeArray[i]));
                    nodes.add(node.right);
                }
                i++;
            }
            nodeList = nodes;
        }
        return root;
    }

    // Encodes a tree to a single string.
    public static String serialize1(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        serialize(root, builder);
        return builder.toString();
    }

    public static void serialize(TreeNode root, StringBuilder builder) {
        if (root == null) {
            builder.append("#").append(",");
            return;
        }
        builder.append(root.val).append(",");
        serialize(root.left, builder);
        serialize(root.right, builder);
    }

    /**
     *Decodes your encoded data to tree.
     **/
    public static TreeNode deserialize1(String data) {
        // 这个地方要这么写，如果直接使用asList的话，下面remove的时候会抛异常java.lang.UnsupportedOperationException
        List<String> strings = new ArrayList<>(Arrays.asList(data.split(",")));
        return deserialize(strings);
    }

    public static TreeNode deserialize(List<String> strings) {
        if ("#".equals(strings.get(0))) {
            strings.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strings.get(0)));
        strings.remove(0);
        root.left = deserialize(strings);
        root.right = deserialize(strings);
        return root;
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}
