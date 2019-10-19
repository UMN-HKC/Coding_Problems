package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0109_ConvertSortedListToBST {

    // approach 1:
    // The idea is to convert list to array so that we can access elements via indices

    // time: O(n)
    // space: O(n)

    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Integer[] arr = list.toArray(new Integer[0]);
        return buildTree(arr, 0, arr.length - 1);
    }
    public TreeNode buildTree(Integer[] arr, int l, int r) {
        if (l > r) return null;
        int m = l + (r - l) / 2;
        TreeNode root = new TreeNode((int)arr[m]);
        root.left = buildTree(arr, l, m - 1);
        root.right = buildTree(arr, m + 1, r);
        return root;
    }

    // approach 2: slow and fast pointers
    // the main idea is to use a slow and fast pointer to find a root node and
    // meanwhile keep a prev pointer so that we can divide the list by setting
    // root's left list as left subtree and root's right subtree as right subtree
    // by making recursive calls

    // time: O(nlogn)
    // space: O(logn)

    public TreeNode sortedListToBST_2(ListNode head) {
        if (head == null) return null;
        return buildTree(head, null);
    }
    public TreeNode buildTree(ListNode head, ListNode tail) {
        // tail is the next of the end of the list
        if (head == tail) return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = buildTree(head, slow);
        root.right = buildTree(slow.next, tail);
        return root;
    }


}
