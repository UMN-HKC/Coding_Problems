package Leetcode;
import java.util.*;

public class P0023_MergeKSortedLists {

    // approach 1: divide and conquer

    // time: O(nlogk)
    // space: O(k)

    public ListNode mergeKLists_1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int start = 0, end = lists.length - 1;
        return mergeTwoLists(lists, start, end);
    }
    public ListNode mergeTwoLists(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        else {
            int m = l + (r - l) / 2;
            ListNode list1 = mergeTwoLists(lists, l, m);
            ListNode list2 = mergeTwoLists(lists, m+1, r);

            ListNode res = new ListNode(-1);
            ListNode itr = res, itr1 = list1, itr2 = list2;
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

    // approach 2: min heap(priority queue)

    // time: O(nlogk)
    // space: O(k)

    public ListNode mergeKLists_2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode res = new ListNode(-1);
        ListNode itr = res;
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            if (pq.isEmpty()) {
                itr.next = cur;
            }
            else {
                ListNode rest = cur.next;
                cur.next = null;
                itr.next = cur;
                itr = itr.next;
                if (rest != null) {
                    pq.offer(rest);
                }
            }
        }
        return res.next;

    }
}
