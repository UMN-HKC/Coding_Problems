package Leetcode;

public class P0148_SortList {

    // approach 1: merge sort (use recursion, space is not optimal), may look into
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

    // approach 2: reverse merge sort

    // time: O(nlogn)
    // space: O(1)

    public ListNode sortList_2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode itr = head;
        int cnt = 1;
        while (itr.next != null) {
            itr = itr.next;
            cnt++;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (int len = 1; len < cnt; len <<= 1) {
            ListNode pre = dummy, cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, len);
                cur = split(right, len);
                pre = merge(left, right, pre);
                pre.next = cur;
            }
        }
        return dummy.next;
    }
    public ListNode split(ListNode head, int n) {
        if (head == null) return null;
        for (int i = 1; i < n && head.next != null; i++) {
            head = head.next;
        }
        ListNode tail = head.next;
        head.next = null;
        return tail;
    }
    public ListNode merge(ListNode left, ListNode right, ListNode head) {
        ListNode cur = head;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
            cur.next = null;
        }
        cur.next = left != null ? left : right;
        while (cur.next != null) cur = cur.next;
        return cur;
    }
}
