package Leetcode;

public class P0019_RemoveNthNodeFromEndOfLinkedList {

    // initial approach
    // the basic idea is to have two node pointers and move one of them n distance first
    // so that the distance between two nodes is n. Then, move both node pointers until
    // the right one's next is null which means we have reached the end.
    // so we delete the slow pointer's next node which is the one we want to delete
    // the trick for this problem is to use a dummy node to help us deal with edge cases
    // eg: [1] 1 / [1,2], 2

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode paceMaker = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < n; i++) {
            paceMaker = paceMaker.next;
        }
        while (paceMaker.next != null) {
            paceMaker = paceMaker.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
