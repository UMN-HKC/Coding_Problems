package Leetcode;

public class P0109_ConvertSortedListToBST {

    // initial approach: recursive
    // the main idea is to use a slow and fast pointer to find a root node and
    // meanwhile keep a prev pointer so that we can divide the list by setting
    // root's left list as left subtree and root's right subtree as right subtree
    // by making recursive calls

    // time: O(nlogn)
    // space: O(logn)

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next);
        if (slow == head) {
            return root;
        }
        else {
            prev.next = null;
            root.left = sortedListToBST(head);
            return root;
        }
    }
}
