package Leetcode;

public class P0143_ReorderList {

    // approach 1:
    // 3 steps: find the middle node -> reverse the later half -> reorder two lists

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        int size = 1;
        ListNode itr = head;
        while (itr.next != null) {
            size++;
            itr = itr.next;
        }
        int move = (size - 1) / 2;
        itr = head;
        // fiund middle
        for (int i = 0; i < move; i++) {
            itr = itr.next;
        }
        // reverse the later half
        ListNode reversed = reverse(itr.next);
        itr.next = null;
        itr = head;
        // reorder
        while (reversed != null) {
            ListNode next = itr.next;
            itr.next = reversed;
            itr = next;
            ListNode reversedNext = reversed.next;
            reversed.next = itr;
            reversed = reversedNext;
        }
    }
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode cur = head, next = head;
        while (cur != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
