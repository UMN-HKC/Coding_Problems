package Leetcode;

public class P0117_PopulatingNextRightPointersInEachNodeII {

    // approach 1: couldn't figure out initially with constant space

    // idea borrowed from discussion board
    // The basic idea is to iterate level by level and when we iterate a parent level,
    // we connect its children level. Use head to help reset itr pointer when done traversing
    // parent level. Use pre to help itr pointer to connect children level.

    public Node connect(Node root) {
        if (root == null) return null;
        // pre is the previous node in the lower level
        Node pre = null;
        // itr is the current node in the upper level
        Node itr = null;
        // head is the leftmost node in the lower level
        Node head = root;

        while (head != null) {
            itr = head;
            pre = null;
            head = null;
            while (itr != null) {
                if (itr.left != null) {
                    if (pre == null) {
                        head = itr.left;
                    }
                    else {
                        // connect previous child to current child
                        pre.next = itr.left;
                    }
                    pre = itr.left;
                }
                if (itr.right != null) {
                    if (pre == null) {
                        head = itr.right;
                    }
                    else {
                        // connect previous child to current child
                        pre.next = itr.right;
                    }
                    pre = itr.right;
                }
                itr = itr.next;
            }
        }
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
