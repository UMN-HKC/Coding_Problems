package Leetcode;

import java.util.Stack;

public class P0510_InorderSuccessorInBSTII {

    // approach 1: initial approach
    // first get the root, and use stack and do a inorder traversal

    // time: O(n)
    // space: O(n)

    public Node inorderSuccessor(Node x) {
        Node root = x;
        while (root.parent != null) root = root.parent;
        Stack<Node> s = new Stack<>();
        Node pre = null;
        while (!s.empty() || root != null) {
            if (root != null) {
                s.push(root);
                root = root.left;
            }
            else {
                // root is only popped here and since it is inorder traversal
                // each time the root popped is a successor of the previous node
                root = s.pop();
                if (pre != null) {
                    if (pre == x) {
                        return root;
                    }
                }
                pre = root;
                root = root.right;
            }
        }
        return root;
    }

    // approach 2:
    // check node's right:
    // 1).node has right, go to right and traverse to leftmost;
    // 2). Node has no right, traverse upwards until there's a value that's greater than x's value

    // time: O(n)
    // space: O(1)

    public Node inorderSuccessor_2(Node x) {
        if (x == null) return null;

        if (x.right == null) {
            Node node = x.parent;
            while (node != null && node.val <= x.val) {
                node = node.parent;
            }
            return node;
        }
        else {
            Node node = x.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
