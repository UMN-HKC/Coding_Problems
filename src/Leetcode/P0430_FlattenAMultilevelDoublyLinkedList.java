package Leetcode;

public class P0430_FlattenAMultilevelDoublyLinkedList {

    // approach 1:
    // note that flattenChild() returns the end node of the flattened list

    public Node flatten(Node head) {
        // thus, we only apply flattenChild() to the head instead of just returning it
        flattenChild(head);
        // eventually, we will still need return the head
        // after all following nodes have been flattened
        return head;
    }
    // this function returns the end of the flattend node list
    public Node flattenChild(Node node) {
        // when node is null, return null
        if (node == null) return node;

        if (node.child == null) {
            // when list has no child, and is the last node, we will return this node
            if (node.next == null) return node;
            // if no child, but have next node, we will apply flattenChild() to the next
            return flattenChild(node.next);
        }
        else {
            // get the tail of this flattened child first
            Node tailOfChild = flattenChild(node.child);
            // if there's no next node, we simply connect current node's next
            // pointer to the flattened child node
            if (node.next == null) {
                node.next = node.child;
                node.next.prev = node;
                node.child = null;
                return tailOfChild;
            }
            // if there's next node, we first update pointers, and then return the
            // last node of the flattened next node.
            else {
                tailOfChild.next = node.next;
                node.next.prev = tailOfChild;
                node.next = node.child;
                node.next.prev = node;
                node.child = null;
                return flattenChild(tailOfChild.next);
            }
        }
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    };
}
