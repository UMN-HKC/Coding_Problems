package Leetcode;

public class P0203_RemoveLinkedListElements {

    // approach 1: iterative(with 3 pointers)

    public ListNode removeElements_1(ListNode head, int val) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head, next = head;
        while (cur != null) {
            next = cur.next;
            if (cur.val == val) {
                if (pre == null) {
                    cur.next = null;
                    head = next;
                }
                else {
                    pre.next = next;
                    cur.next = null;
                }
                cur = next;
            }
            else {
                pre = cur;
                cur = next;
            }
        }
        return head;
    }
    // approach 2: iterative, use a dummy node to help

    public ListNode removeElements_2(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null) {
            if (head.val == val) {
                pre.next = head.next;
                head = head.next;
            }
            else {
                pre = head;
                head = head.next;
            }
        }
        return dummy.next;
    }

    // approach 3: recursion

    public ListNode removeElements_3(ListNode head, int val) {
        if (head == null) return null;
        if (head.val != val) head.next = removeElements_3(head.next, val);
        else return removeElements_3(head.next, val);
        return head;
    }
}
