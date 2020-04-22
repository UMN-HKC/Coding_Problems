package Leetcode;

import java.util.*;

public class P0428_SerializeAndDeserializeNaryTree {

    // approach 1: using queue (preorder traversal)

    // The serialization schema for this approach is that we will build the
    // string such that each value is separated by a coma. The first value
    // is the root value, the second value is the number of children nodes
    // root node has. Then, we will recursively build the this string.

    // When deserializing, we split the string by the coma, and store
    // all values into a queue. Since we know the first value is the
    // root value and second value is the number of children nodes the
    // root has, it is easy to recursively build tree.

    // time: O(n) where n is the number of nodes in the tree
    // space: O(n)

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }
    private void serializeHelper(Node root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
        }
        else {
            sb.append(root.val).append(",");
            int size = root.children.size();
            sb.append(size).append(",");
            for (int i = 0; i < size; i++) {
                serializeHelper(root.children.get(i), sb);
            }
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        List<String> datas = Arrays.asList(data.split(","));
        Queue<String> q = new LinkedList<>();
        q.addAll(datas);
        return deserializeHelper(q);
    }
    private Node deserializeHelper(Queue<String> q) {
        String value = q.poll();
        if (value.equals("#")) {
            return null;
        }
        Node root = new Node(Integer.parseInt(value));
        List<Node> children = new ArrayList<>();
        int size = Integer.parseInt(q.poll());
        for (int i = 0; i < size; i++) {
            children.add(deserializeHelper(q));
        }
        root.children = children;
        return root;
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
