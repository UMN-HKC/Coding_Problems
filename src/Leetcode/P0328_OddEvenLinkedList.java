package Leetcode;

public class P0328_OddEvenLinkedList {

    // initial approach
    // pay attention to the potential null pointers in the end

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode evenMark = head.next, odd = head, even = head.next;
        while (even != null) {
            odd.next = even.next;
            if (odd.next != null) {
                odd = odd.next;
            }
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenMark;
        return head;
    }
}
