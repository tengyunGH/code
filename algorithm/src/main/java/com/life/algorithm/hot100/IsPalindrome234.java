package com.life.algorithm.hot100;

/**
 * @author tengyun
 * @date 2021/2/20 20:19
 **/
public class IsPalindrome234 {

    public static void main(String[] args) {
        IsPalindrome234 isPalindrome234 = new IsPalindrome234();
        ListNode head = new ListNode(1, new ListNode(1, null));
        isPalindrome234.isPalindrome(head);
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        boolean flag = true;
        ListNode fast = head, late = head, pre = null, temp = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            late = late.next;
            temp.next = pre;
            pre = temp;
            temp = late;
        }
        if (fast != null) {
            // 有奇数个节点 late 指向的是正中间
            late = late.next;
        }
        ListNode proxy;
        while (late != null) {
            proxy = pre;
            if (pre.val != late.val) {
                flag = false;
            }
            pre = pre.next;
            late = late.next;
            proxy.next = temp;
            temp = proxy;
        }
        //
        return flag;
    }


     public static class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
}
