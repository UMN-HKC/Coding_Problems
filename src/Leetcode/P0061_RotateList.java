package Leetcode;

public class P0061_RotateList {

    // approach 1:
    // need to pay attention to value of k. If the value of k is multiples of list size
    // we simply return the head, because after shifting, the list will still be same

    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        ListNode itr = head;
        int size = 1;
        while (itr.next != null) {
            size++;
            itr = itr.next;
        }
        if (k % size == 0) return head;
        k %= size;
        ListNode newTail = head;
        int move = size - k;
        for (int i = 1; i < move; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        itr.next = head;
        return newHead;
    }
}
