package Leetcode;

import java.util.Stack;

public class P0426_ConvertBinarySearchTreeToSortedDoublyLinkedList {


    // approach 1:
    // the basic idea is to do inorder traversal and meanwhile keep a pre
    // variable so that we could connect nodes as doubly linked list when
    // we do the traversal

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        Node head = null, tail = null;
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        head = stack.peek();
        while (root != null || !stack.empty()) {
            if (root == null) {
                root = stack.pop();
                if (pre != null) {
                    pre.right = root;
                    root.left = pre;
                }
                pre = root;
                root = root.right;
            }
            // note that when we are going down the left subtree, we do not
            // set pre node at this time since pre node should be set only
            // when we are doing the actual inorder travel
            else {
                stack.push(root);
                root = root.left;
            }
        }
        head.left = pre;
        pre.right = head;
        return head;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
}
