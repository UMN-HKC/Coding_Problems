package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P1490_CloneNaryTree {

    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val,ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // approach 1:

    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }
        Node rootCopy = new Node(root.val);
        deepCopy(root, rootCopy);
        return rootCopy;
    }
    private void deepCopy(Node root, Node rootCopy) {
        if (root.children == null || root.children.size() == 0) {
            return;
        }
        List<Node> childrenCopy = new ArrayList<>();
        for (Node child : root.children) {
            Node childCopy = new Node(child.val);
            childrenCopy.add(childCopy);
            deepCopy(child, childCopy);
        }
        rootCopy.children = childrenCopy;
    }

    // approach 2: simpler

    public Node cloneTree_2(Node root) {
        if (root == null) {
            return null;
        }
        Node rootCopy = new Node(root.val);
        List<Node> childrenCopy = new ArrayList<>();
        for (Node child : root.children) {
            childrenCopy.add(cloneTree(child));
        }
        rootCopy.children = childrenCopy;
        return rootCopy;
    }
}
