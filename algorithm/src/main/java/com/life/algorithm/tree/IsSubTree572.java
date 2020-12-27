package com.life.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tengyun
 * @date 2020/12/20 10:53
 **/
public class IsSubTree572 {

    public static void main(String[] args) {
        TreeNode snode1 = new TreeNode(1,null, null);
        TreeNode snode2 = new TreeNode(2,null, null);
        TreeNode snode4 = new TreeNode(4, snode1, snode2);
        TreeNode snode5 = new TreeNode(5, null, null);
        TreeNode snode = new TreeNode(3, snode4, snode5);

        TreeNode tnode1 = new TreeNode(1, null, null);
        TreeNode tnode2 = new TreeNode(2, null, null);
        TreeNode tnode = new TreeNode(4, tnode1, tnode2);
        IsSubTree572 isSubTree572 = new IsSubTree572();
        isSubTree572.isSubtree2(snode, tnode);
        System.out.println("yes");
    }

    private List<String> sList = new ArrayList<>();
    private List<String> tList = new ArrayList<>();

    public boolean isSubtree2(TreeNode s, TreeNode t) {
        serizal(s, null, sList);
        serizal(t, null, tList);
        String tRoot = String.valueOf(t.val);
        for (int i = 0; i < sList.size();) {
            if (sList.get(i).equals(tRoot)) {
                int z = ++i;
                int j = 1;
                for (; j < tList.size() && z < sList.size(); j++, z++) {
                    if (!tList.get(j).equals(sList.get(z))) {
                        break;
                    }
                }
                if (j == tList.size()) {
                    return true;
                }
                continue;
            }
            i++;
        }
        return false;
    }

    public void serizal(TreeNode node, Boolean isLeft, List<String> list) {
        if (node == null) {
            if (isLeft) {
                list.add("lnull");
            } else {
                list.add("rnull");
            }
            return;
        }
        String s = "";
        list.add(String.valueOf(node.val));
        serizal(node.left, true, list);
        serizal(node.right, false, list);
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (is(s, t)) {
            return true;
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    public boolean is(TreeNode s, TreeNode t) {
        if(s != null && t != null && s.val == t.val) {
            return is(s.left, t.left) && is(s.right, t.right);
        }
        return s == null && t == null;
    }

}
