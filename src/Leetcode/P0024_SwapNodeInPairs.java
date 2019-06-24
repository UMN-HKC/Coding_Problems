package Leetcode;

public class P0024_SwapNodeInPairs {

    // initial approach: iterative
    // used prev and third to help with restructure pointers after swapping

    public ListNode swapPairs_iterative(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode first = head, second = head.next, prev = null, third = null;
        // set new head
        head = second;
        while (first != null && second != null) {
            third = second.next;
            second.next = first;
            first.next = third;
            // redirect previous node to point to swapped pair
            if (prev != null) prev.next = second;
            // if no further swaps needed, return
            if (third == null) return head;
            prev = first;
            first = third;
            second = third.next;
        }
        return head;
    }

    // approach 2: recursion

    public ListNode swapPairs_recursion(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode rest = swapPairs_recursion(head.next.next);
        ListNode newHead = head.next;
        head.next.next = head;
        head.next = rest;
        return newHead;
    }


}
