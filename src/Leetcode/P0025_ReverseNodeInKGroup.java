package Leetcode;

public class P0025_ReverseNodeInKGroup {

    // approach 1: recursive approach

    public ListNode reverseKGroup(ListNode head, int k) {
        int toReverse = k - 1;
        if (toReverse == 0 || head == null) return head;
        ListNode tail = head;
        while (toReverse > 0 && tail.next != null) {
            tail = tail.next;
            toReverse--;
        }
        if (toReverse > 0) return head;
        ListNode nextHead = reverseKGroup(tail.next, k);
        ListNode pre = null, cur = head, next = cur;
        while (cur != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            if (pre == tail) {
                head.next = nextHead;
                break;
            }
        }
        return pre;
    }

    // approach 2: iterative approach
    // The basic idea is to keep track of multiple nodes that would play roles in reversing
    // sublist and reconnecting. Use a helper function to reverse the sublist and return
    // a pair of nodes(new head and new tail). And then reconnect original list with reversed
    // sublist and then move pointers to the next node of the tail of the reversed sublist.

    public ListNode reverseKGroup_2(ListNode head, int k) {
        if (k == 1) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while (cur != null) {
            int cnt = 1;
            while (cur != null && cnt < k) {
                cur = cur.next;
                cnt++;
            }
            if (cur != null && cnt == k) {
                ListNode next = cur.next;
                cur.next = null;
                ListNode nextHead = pre.next;
                pre.next = null;
                // reverse sublist and return new head and new tail
                ListNode[] revList = reverse(nextHead, cur);
                // reconnect head and tail of the reversed list
                // and move cur pointer to the next of tail of reversed list
                pre.next = revList[0];
                revList[1].next = next;
                pre = revList[1];
                cur = next;
            }
            else {
                break;
            }
        }
        return dummy.next;
    }
    public ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode pre = null, cur = head, next = head;
        while (cur != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{pre, head};
    }
}
