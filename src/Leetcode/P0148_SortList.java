package Leetcode;

public class P0148_SortList {

    // Approach 1: merge sort (use recursion, space is not optimal), may look into
    // iterative approach later if have time.


    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHead = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(secondHead);
        return merge(left, right);
    }
    public ListNode merge(ListNode left, ListNode right) {

        ListNode res = new ListNode(-1);
        ListNode leftItr = left, rightItr = right, resItr = res;
        while (leftItr != null || rightItr != null) {
            if (leftItr != null && rightItr != null) {
                if (leftItr.val <= rightItr.val) {
                    resItr.next = leftItr;
                    resItr = resItr.next;
                    leftItr = leftItr.next;
                    resItr.next = null;
                }
                else {
                    resItr.next = rightItr;
                    resItr = resItr.next;
                    rightItr = rightItr.next;
                    resItr.next = null;
                }
            }
            else if (leftItr != null) {
                resItr.next = leftItr;
                break;
            }
            else {
                resItr.next = rightItr;
                break;
            }
        }
        return res.next;
    }
}
