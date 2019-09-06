package Leetcode;

public class P0147_InsertionSortList {

     // approach 1:

    public ListNode insertionSortList(ListNode head) {
        // pre is the end of the sorted part
        ListNode pre = head, cur = head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        while (cur != null) {
            if (cur == pre) {
                cur = cur.next;
            }
            else {
                // current node is at the right place
                // so go to the next one
                if (cur.val >= pre.val) {
                    pre = pre.next;
                    cur = cur.next;
                }
                // need to find the right place to insert the current node
                else {
                    pre.next = cur.next;
                    ListNode itr = dummy;
                    while (itr.next != null && itr.next.val < cur.val) {
                        itr = itr.next;
                    }
                    cur.next = itr.next;
                    itr.next = cur;
                    cur = pre.next;
                }
            }
        }
        return dummy.next;
    }
}
