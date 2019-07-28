package Leetcode;

public class P0021_MergeTwoSortedLists {

    // approach 1: two pointers

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode itr = res, itr1 = l1, itr2 = l2;
        while (itr1 != null || itr2 != null) {
            if (itr1 != null && itr2 != null) {
                if (itr1.val <= itr2.val) {
                    itr.next = itr1;
                    itr1 = itr1.next;
                }
                else {
                    itr.next = itr2;
                    itr2 = itr2.next;
                }
            }
            else if (itr1 != null) {
                itr.next = itr1;
                itr1 = itr1.next;
            }
            else {
                itr.next = itr2;
                itr2 = itr2.next;
            }
            itr = itr.next;
        }
        return res.next;
    }
}
