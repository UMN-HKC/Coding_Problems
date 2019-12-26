package Leetcode;
import java.util.*;

public class P0138_CopyListWithRandomPointer {

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    // approach 1: initial approach
    // the basic idea is to iterate the list first and make copy of the original list
    // and store into hash map. Then, iterate second time to copy the random pointers
    
    public Node copyRandomList(Node head) {
        Node copy = new Node(-1, null, null);
        Node itr = head, copyItr = copy;
        Map<Node, Node> map = new HashMap<>();
        while (itr != null) {
            copyItr.next = new Node(itr.val, null, null);
            copyItr = copyItr.next;
            map.put(itr, copyItr);
            itr = itr.next;
        }
        itr = head;
        copyItr = copy.next;
        while (itr != null) {
            if (itr.random != null) {
                copyItr.random = map.get(itr.random);
            }
            itr = itr.next;
            copyItr = copyItr.next;
        }
        return copy.next;
    }

    // approach 2: O(1) extra space
    // 3 iterations:
    // - make a copy node for each original node which follows directly after original node
    // - connect each copy node to its respective random copy node
    // - extract copy list and restore original list

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = cur.next.next;
        }
        Node copy = head.next;
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                copy.random = cur.random.next;
            }
            cur = cur.next.next;
            if (cur != null) copy = copy.next.next;
        }
        Node dummy = new Node(-1);
        Node itr = dummy;
        cur = head;
        while (cur != null) {
            itr.next = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            itr = itr.next;
        }
        return dummy.next;
    }
}
