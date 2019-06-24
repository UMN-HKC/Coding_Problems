package Leetcode;

public class P0142_LinkedListCycleII {

    // the basic idea is to realize that when there's a cycle in the linkedlist, the time the
    // slow and fast nodes meet each other is when the fast node has outrun the slow node
    // by one cycle length. Set slow pointer to head again, and now move each node one step
    // at a time, the next time they meet each other, that's where the entry to the cycle begins

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode slow = head, fast = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
