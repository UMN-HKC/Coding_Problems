package Leetcode;

public class P0025_ReverseNodeInKGroup {

    // approach 1: recursive approach

    public ListNode reverseKGroup(ListNode head, int k) {
        int toReverse = k - 1;
        if (toReverse == 0 || head == null) return head;
        ListNode tail = head;
        while (toReverse > 0 && tail.next != null) {
            tail = tail.next;
            toReverse--;
        }
        if (toReverse > 0) return head;
        ListNode nextHead = reverseKGroup(tail.next, k);
        ListNode pre = null, cur = head, next = cur;
        while (cur != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            if (pre == tail) {
                head.next = nextHead;
                break;
            }
        }
        return pre;
    }

    // approach 2: iterative approach
    // the main idea is to visualize reversing the linked list as the following
    // and use a dummy node to help us as well. The key is to reverse list[begin, end]
    // with begin and end exclusive. And the reverse() helper function will return
    // the 'dummy' starting node for the next reverse process of the list

    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */

    public ListNode reverseKGroup_iterative(ListNode head, int k) {
        if (k < 2 || head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        ListNode begin = dummy;
        dummy.next = head;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            }
            else {
                head = head.next;
            }
        }
        return dummy.next;
    }
    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode pre, cur, next, first;
        pre = begin;
        first = cur = next = begin.next;
        while (cur != end) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        begin.next.next = end;
        begin.next = pre;
        return first;
    }
}
