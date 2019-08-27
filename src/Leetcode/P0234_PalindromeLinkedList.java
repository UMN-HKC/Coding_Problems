package Leetcode;

public class P0234_PalindromeLinkedList {

    // approach 1:

    // - split the list to two parts
    // - reverse the second list
    // - iterate two lists and check for palindrome

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        // note that we initialize fast = head.next
        // because if we initialize fast = head, for case like
        // [1, 2], the second list we get will be null while
        // the first list has both two nodes
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        // reverse the second list
        ListNode pre = null, cur = head2, next = head2;
        while (next != null) {
            next = next.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // check if palindrome
        head2 = pre;
        while (head != null && head2 != null) {
            if (head.val != head2.val) return false;
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }
}
