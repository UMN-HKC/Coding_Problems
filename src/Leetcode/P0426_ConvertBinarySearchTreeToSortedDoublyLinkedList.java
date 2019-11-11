package Leetcode;

import java.util.Stack;

public class P0426_ConvertBinarySearchTreeToSortedDoublyLinkedList {

    // approach 1: divide and conquer, post order
    // Step 1: Divide:
    // We divide tree into three parts: left subtree, root node, right subtree.
    // Convert left subtree into a circular doubly linked list as well as the right subtree.
    // Be careful. You have to make the root node become a circular doubly linked list.
    // Step 2: Conquer:
    // Firstly, connect left circular doubly linked list with the root circular doubly linked list.
    // Secondly, connect them with the right circular doubly linked list. Here we go!

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node head = treeToDoublyList(root.left);
        Node next = treeToDoublyList(root.right);
        root.left = root;
        root.right = root;
        return connect(head, connect(root, next));
    }
    private Node connect(Node l1, Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        Node TailOfL1 = l1.left;
        l1.left = l2.left;
        l2.left.right = l1;
        TailOfL1.right = l2;
        l2.left = TailOfL1;
        return l1;
    }

    // approach 1:
    // the basic idea is to do inorder traversal and meanwhile keep a pre
    // variable so that we could connect nodes as doubly linked list when
    // we do the traversal

    public Node treeToDoublyList_2(Node root) {
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
