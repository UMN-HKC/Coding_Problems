package Leetcode;

public class P0116_PopulatingNextRightPointersInEachNode {

    // approach 1: iterative

    public Node connect(Node root) {
        if (root == null) return null;
        Node itr = root;
        Node first = root.left;
        while (itr.left != null && itr.right != null) {
            itr.left.next = itr.right;
            // end of current level
            if (itr.next == null) {
                itr = first;
                first = itr.left;
            }
            // within the current level
            else {
                // connect consecutive nodes' children(set left node's right child's next to right node's left child)
                itr.right.next = itr.next.left;
                itr = itr.next;
            }
        }
        return root;
    }

    // approach 2: recursive
    // preorder

    public Node connect_rec(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect_rec(root.left);
        connect_rec(root.right);
        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
