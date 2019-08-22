package Leetcode;

public class P0082_RemoveDuplicatesFromSortedListII {

    // approach 1:

    // Use a dummy node to ease the implementation. Use pre, cur, next nodes.
    // cur node is the potential start of duplicate, and so we place pre
    // node before cur node to remove all duplicates from start if encountered.
    // when we encounter a duplicate node(when next.val == cur.val), we start
    // move next until it is null or next.val != cur.val, which means next is
    // on the right of the end of duplicate value node. So we connect pre to next
    // to skip all duplicates in the middle.
    // note that after removing all middle duplicates, we reset cur to pre

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode next = cur.next;
        while (next != null) {
            if (cur.val == next.val) {
                while (next != null && cur.val == next.val) {
                    next = next.next;
                }
                pre.next = next;
                cur = pre;
            }
            else {
                pre = cur;
                cur = cur.next;
                next = next.next;
            }
        }
        return dummy.next;
    }
}
