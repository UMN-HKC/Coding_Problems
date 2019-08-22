package Leetcode;

public class P0086_PartitionList {

    // approach 1: The basic idea is to, first initialize two dummy nodes that
    // represent a list whose nodes' values are all smaller than x, and a list
    // whose nodes' values are all greater than y. Then, iterate through the
    // original list, and in terms of node's value, we will point smaller list
    // or greater list to this current node, and update pointers.


    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;

        ListNode smaller = new ListNode(-1);
        ListNode greater = new ListNode(-1);
        ListNode smallerItr = smaller, greaterItr = greater;
        ListNode pre = head;
        while (head.next != null) {
            if (head.val < x) {
                smallerItr.next = head;
                smallerItr = smallerItr.next;
            }
            else {
                greaterItr.next = head;
                greaterItr = greaterItr.next;
            }
            head = head.next;
            pre.next = null;
            pre = head;
        }
        if (head.val < x) {
            smallerItr.next = head;
            smallerItr = smallerItr.next;
        }
        else {
            greaterItr.next = head;
            greaterItr = greaterItr.next;
        }
        smallerItr.next = greater.next;
        return smaller.next;
    }
}
