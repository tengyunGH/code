package com.life.algorithm.tree;

/**
 * @author tengyun
 * @date 2020/12/13 18:30
 **/
public class BSTreeDeleteOne {

    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode next;

        public TreeNode() {}

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right, TreeNode next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == q) {
            return true;
        }
        if (p != null && q != null) {
            if (p.val == q.val) {
                return isSameTree(p.left, q.left) && isSameTree(p.right , q.right);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(80, null, null, null);
        TreeNode node5 = new TreeNode(60, null, null, null);
        TreeNode node4 = new TreeNode(40, null, null, null);
        TreeNode node3 = new TreeNode(70, node5, node6, null);
        TreeNode node2 = new TreeNode(30, null, node4, null);
        TreeNode node1 = new TreeNode(50, node2, node3, null);
        deleteNode(node1, 50);
        System.out.println(node1.val);
    }
    private static TreeNode rootNode;

    public static TreeNode deleteNode(TreeNode root, int key) {
        rootNode = root;
        delete(root, null, true, key);
        return rootNode;
    }

    public static void delete(TreeNode node, TreeNode parent, boolean isLeft, int key) {
        if (node == null) {
            return;
        }
        if (node.val < key) {
            delete(node.right, node, false, key);
            return;
        }
        if (node.val > key) {
            delete(node.left, node, true, key);
            return;
        }
        // node.val == key
        if (node.left == null && node.right == null) {
            if (parent == null) {
                rootNode = null;
                return;
            }
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (node.left == null) {
            if (parent == null) {
                rootNode = node.right;
                return;
            }
            if (isLeft) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else if (node.right == null) {
            if (parent == null) {
                rootNode = node.left;
                return;
            }
            if (isLeft) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        } else {
            // 找到node的右子树中最小的节点，并用该节点代替
            TreeNode minNode = node.right;
            TreeNode minNodeParent = node;
            while(minNode.left != null) {
                minNodeParent = minNode;
                minNode = minNode.left;
            }
            if (parent != null) {
                if (isLeft) {
                    parent.left = minNode;
                } else {
                    parent.right = minNode;
                }
            } else {
                rootNode = minNode;
            }
            minNode.left = node.left;
            if (minNodeParent != node) {
                minNodeParent.left = minNode.right;
                minNode.right = node.right;
            }
            node.left = null;
            node.right = null;
        }
    }


}
