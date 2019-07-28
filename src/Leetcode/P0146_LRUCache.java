package Leetcode;
import java.util.*;

public class P0146_LRUCache {

    // approach 1: initial approach(hashmap + doubly linked list)
    // initially used java's deque structure but later realized it is taking
    // O(n) time to remove a specific element from the list

    class LRUCache {
        class Node {
            Node front;
            Node back;
            int key;
            int val;
            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        Node head;
        Node tail;
        Map<Integer, Node> map;
        int cap;
        public LRUCache(int capacity) {
            cap = capacity;
            map = new HashMap<>();
            head = new Node(-1,-1);
            tail = new Node(-1,-1);
            head.back = tail;
            tail.front = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                // update the key in the list
                removeFromList(key);
                addToFrontList(key);
                return map.get(key).val;
            }
            return -1;
        }

        public void put(int key, int value) {

            if (map.containsKey(key)) {
                removeFromList(key);
                addToFrontList(key);
                map.get(key).val = value;
            }
            else {
                if (map.size() == cap) {
                    int keyToBeRemoved = tail.front.key;
                    removeFromList(keyToBeRemoved);
                    map.remove(keyToBeRemoved);
                }
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToFrontList(key);
            }
        }
        public void removeFromList(int key) {
            // remove it from list
            Node cur = map.get(key);
            Node front = cur.front;
            Node back = cur.back;
            front.back = back;
            back.front = front;

        }
        public void addToFrontList(int key) {
            // add to the front
            Node cur = map.get(key);
            head.back.front = cur;
            cur.back = head.back;
            cur.front = head;
            head.back = cur;
        }
    }
}
