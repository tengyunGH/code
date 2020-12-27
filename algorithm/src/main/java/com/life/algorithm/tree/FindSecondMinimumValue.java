package com.life.algorithm.tree;

/**
 * @author tengyun
 * @date 2020/12/25 8:41
 **/
public class FindSecondMinimumValue {

    public static void main(String[] args) {
        FindSecondMinimumValue findSecondMinimumValue = new FindSecondMinimumValue();
        Node node14 = new Node(3);
        Node node15 = new Node(2);
        Node node7 = new Node(6, null, null);
        Node node6 = new Node(5, null, null);
        Node node5 = new Node(2, node14, node15);
        Node node4 = new Node(10, null, null);
        Node node3 = new Node(5, node6, node7);
        Node node2 = new Node(2, node4, node5);
        Node node1 = new Node(2, node2, node3);
        int secondMinimumValue = findSecondMinimumValue.findSecondMinimumValue(node1);
        System.out.println(secondMinimumValue);
    }

    public int findSecondMinimumValue(Node root) {
        if (root == null || root.left == null) {
            return -1;
        }
        int secondLeftMin = -1, secondRightMin = -1, temp;
        // 找到与根节点相同值的这棵子树的最小二值
        if (root.e == root.left.e) {
            secondLeftMin = findSecondMinimumValue(root.left);
        } else {
            secondLeftMin = (int) root.left.e;
        }
        if (root.e == root.right.e) {
            secondRightMin = findSecondMinimumValue(root.right);
        } else {
            secondRightMin = (int) root.right.e;
        }
        if (secondLeftMin == -1 && secondRightMin == -1) {
            if (root.left.e == root.right.e) {
                return -1;
            } else {
                return Math.max((Integer) root.left.e, (Integer) root.right.e);
            }
        } else if (secondLeftMin != -1 && secondRightMin != -1) {
            return Math.min(secondLeftMin, secondRightMin);
        } else {
            return Math.max(secondLeftMin, secondRightMin);
        }
    }

}
