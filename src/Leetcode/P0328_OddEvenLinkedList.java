package Leetcode;

public class P0328_OddEvenLinkedList {

    // approach 1:
    // The basic idea is to keep an even list and an odd list, and after building two
    // lists, connect odd's tail to even's head.
    // Note that the only thing even node does is to connect its next to next even node
    // and the only thing odd node does is to connect its next to next odd node.

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode even = head.next;
        ListNode evenHead = even;
        ListNode odd = head;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
