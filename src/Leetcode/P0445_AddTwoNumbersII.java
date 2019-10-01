package Leetcode;

import java.util.Stack;

public class P0445_AddTwoNumbersII {

    // approach 1:initial approach (modifying the input lists)

    // The idea is to reverse the two input lists and then build our result list.
    // Finally, reverse the result list and return it

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;
        ListNode reversedL1 = reverse(l1);
        ListNode reversedL2 = reverse(l2);
        int carry = 0;
        while (reversedL1 != null || reversedL2 != null) {
            int sum = carry;
            if (reversedL1 != null) {
                sum += reversedL1.val;
                reversedL1 = reversedL1.next;
            }
            if (reversedL2 != null) {
                sum += reversedL2.val;
                reversedL2 = reversedL2.next;
            }
            carry = sum / 10;
            itr.next = new ListNode(sum % 10);
            itr = itr.next;
        }
        if (carry != 0) {
            itr.next = new ListNode(carry);
        }
        return reverse(dummy.next);
    }
    public ListNode reverse(ListNode node) {
        ListNode pre = null, cur = node, next = node;
        while (cur != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // approach 2: stack (without modifying input lists)

    public ListNode addTwoNumbers_2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        ListNode itr1 = l1, itr2 = l2;
        while (itr1 != null || itr2 != null) {
            if (itr1 != null) {
                s1.push(itr1.val);
                itr1 = itr1.next;
            }
            if (itr2 != null) {
                s2.push(itr2.val);
                itr2 = itr2.next;
            }
        }
        ListNode head = new ListNode(-1);
        int carry = 0;
        while (!s1.empty() || !s2.empty()) {
            int sum = carry;
            if (!s1.empty()) {
                sum += s1.pop();
            }
            if (!s2.empty()) {
                sum += s2.pop();
            }
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head.next;
            head.next = newNode;
        }
        if (carry != 0) {
            ListNode newNode = new ListNode(carry);
            newNode.next = head.next;
            head.next = newNode;
        }
        return head.next;
    }

}
