package Leetcode;

public class P0237_DeleteNodeInALinkedList {

    // initial approach
    // since we do not have access to previous nodes, the only thing we can do is to
    // modify the current node and its following nodes. To delete the current node and
    // since we are not restricted from modifying values, we can simply move the target
    // node's value to the end node and delete the end node.

    public void deleteNode(ListNode node) {

        while (node.next != null && node.next.next != null) {
            int temp = node.next.val;
            node.next.val = node.val;
            node.val = temp;
            node = node.next;
        }
        if (node.next.next == null) {
            node.val = node.next.val;
            node.next = null;
        }
        return;
    }
}
