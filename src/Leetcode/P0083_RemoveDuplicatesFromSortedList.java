package Leetcode;

public class P0083_RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode itr = head;
        while (itr.next != null) {
            if (itr.next.val == itr.val) {
                itr.next = itr.next.next;
            }
            else {
                itr = itr.next;
            }
        }
        return head;
    }
}
