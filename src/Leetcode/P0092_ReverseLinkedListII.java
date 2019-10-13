package Leetcode;

public class P0092_ReverseLinkedListII {

    // initial approach:
    // The key to do it in one pass is that we need to keep track of list before m and at m
    // so that after we reverse the target range of list, we can connect them back.

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        int cnt = 1;
        // move pre pointer to the node before the beginning
        // of the sublist that is to be reversed
        while (cnt < m) {
            pre = pre.next;
            cnt++;
        }
        // reference the reconnecting nodes first before reversing the sublist
        // after referencing, the rest is the same as reversing a list.
        ListNode leftTail = pre, nextTail = pre.next, cur = pre.next, next = pre.next;
        while (cnt <= n) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            cnt++;
        }
        // reconnect
        nextTail.next = next;
        leftTail.next = pre;
        return dummy.next;
    }
}
