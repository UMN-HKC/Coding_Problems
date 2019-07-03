package Leetcode;
import java.util.*;

public class P0297_SerializeAndDeserializeBinaryTree {

    // initial approach: preorder traversal
    // trick is to use a queue to store split strings when doing deserialization
    // we pass the queue by reconstructing the tree, each time we poll one string
    // from the queue. If it is "#", we return null, otherwise we create a root node
    // and recursively constructing its left and right subtree. This is also
    // inorder which aligned with the sequence we add strings to our queue.

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#->";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val + "->");
        sb.append(serialize(root.left));
        sb.append(serialize(root.right));
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split("->")));
        return deserializeHelper(queue);
    }
    public TreeNode deserializeHelper(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("#")) return null;
        else {
            TreeNode root = new TreeNode(Integer.valueOf(s));
            root.left = deserializeHelper(queue);
            root.right = deserializeHelper(queue);
            return root;
        }
    }
}
