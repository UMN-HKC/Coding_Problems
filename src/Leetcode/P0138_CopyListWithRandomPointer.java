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
}
