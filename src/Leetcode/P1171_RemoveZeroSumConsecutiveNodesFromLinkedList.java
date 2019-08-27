package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P1171_RemoveZeroSumConsecutiveNodesFromLinkedList {

    // approach 1: prefix sum + hashmap
    // the basic idea is to apply prefix sum to solve it in a linked list
    // Iterate through linked list and use hashmap to store previous sum
    // when we encounter a sum that has been previously stored, we know that
    // the difference between is 0, so we remove the middle list.
    // Note that, before we remove the middle list, we first remove
    // old references first by iterating through the middle list and
    // delete old sum from map.

    // time: O(n) since each node is visited at most twice

    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        map.put(0, dummy);
        int sum = 0;
        while (head != null) {
            sum += head.val;
            if (map.containsKey(sum)) {
                ListNode front = map.get(sum).next;
                int val = sum;
                // remove bad map references
                while (front != head) {
                    val += front.val;
                    map.remove(val);
                    front = front.next;
                }
                map.get(sum).next = head.next;
                head.next = null;
                head = map.get(sum).next;
            }
            else {
                map.put(sum, head);
                head = head.next;
            }
        }
        return dummy.next;
    }
}
