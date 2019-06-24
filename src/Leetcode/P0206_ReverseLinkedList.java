package Leetcode;

public class P0206_ReverseLinkedList {

    // approach 1: recursive
    public ListNode reverseList_recursive(ListNode head) {
        if (head == null) return null;
        ListNode newHead = reverseList_recursive(head.next);
        if (head.next != null) {
            head.next.next = head;
            head.next = null;
        }
        return newHead == null ? head : newHead;
    }

    // approach 2: iterative

    public ListNode reverseList_iterative(ListNode head) {
        if (head == null) return null;
        ListNode prev = null, cur = head, next = head;
        while (cur.next != null) {
            next = next.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        cur.next = prev;
        return cur;
    }

}
