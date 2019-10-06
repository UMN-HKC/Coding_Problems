package Leetcode;
import java.util.*;

public class P0023_MergeKSortedLists {

    // approach 1: divide and conquer

    // time: O(n * k * log(k))
    // The time complexity should be O(n * k * log(k)) where n is the average length of a
    // list and k is the number of lists . It is basically merge sort. At leaf level of
    // merge sort, we will have k independent lists lying there waiting to be merged and
    // return to the previous level/stack. Combining two independent lists at this level
    // takes O(2 * n) time since there are n nodes in each list, and there are k / 2 combining
    // operations we need to do at this level which results in O(2 * n * k / 2) => O(n * k).
    // The stack depth is log(k). So overall the time complexity should be O(n * k * log(k)).

    // space: O(log(k))

    public ListNode mergeKLists_1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeSort(lists, 0, lists.length - 1);
    }
    public ListNode mergeSort(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        else if (l == r - 1) {
            return merge(lists[l], lists[r]);
        }
        else {
            int mid = l + (r - l) / 2;
            ListNode left = mergeSort(lists, l, mid);
            ListNode right = mergeSort(lists, mid + 1, r);
            ListNode combine = merge(left, right);
            return combine;
        }
    }
    public ListNode merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        if (list1.val < list2.val) {
            list1.next = merge(list1.next, list2);
            return list1;
        }
        else {
            list2.next = merge(list1, list2.next);
            return list2;
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
