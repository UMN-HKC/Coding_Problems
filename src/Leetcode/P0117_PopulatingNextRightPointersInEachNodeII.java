package Leetcode;

public class P0117_PopulatingNextRightPointersInEachNodeII {

    // approach 1: use dummy node to help traverse

    // The basic idea is to iterate level by level and when we iterate a parent level,
    // create a dummy node for the next level, and use a dummy itr as well to connect
    // next level as we traverse current level.

    public Node connect(Node root) {
        if (root == null) return null;
        Node cur = root;
        while (cur != null) {
            Node nextLevelDummyHead = new Node(-1);
            Node nextLevelItr = nextLevelDummyHead;
            Node itr = cur;
            while (itr != null) {
                if (itr.left != null) {
                    nextLevelItr.next = itr.left;
                    nextLevelItr = nextLevelItr.next;
                }
                if (itr.right != null) {
                    nextLevelItr.next = itr.right;
                    nextLevelItr = nextLevelItr.next;
                }
                itr = itr.next;
            }
            cur = nextLevelDummyHead.next;

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
