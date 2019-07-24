package Leetcode;

public class P0002_AddTwoNumbers {

    // approach 1
    // simple, but note that when carry is not 0, we will still go into the loop
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(-1);
        ListNode itr1 = l1, itr2 = l2, itr3 = res;;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            carry = sum / 10;
            itr3.next = new ListNode(sum % 10);
            itr3 = itr3.next;
        }
        return res.next;
    }
}
