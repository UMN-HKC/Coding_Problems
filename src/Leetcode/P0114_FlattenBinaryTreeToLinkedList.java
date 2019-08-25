package Leetcode;

public class P0114_FlattenBinaryTreeToLinkedList {


    // approach 1: post order

    public void flatten(TreeNode root) {
        flattenHelper(root);
        return;
    }
    public TreeNode flattenHelper(TreeNode root) {
        if (root == null) return null;
        if (root.left == null) {
            if (root.right == null) return root;
            return flattenHelper(root.right);
        }
        else {
            TreeNode tailOfRight = flattenHelper(root.right);
            TreeNode tailOfLeft = flattenHelper(root.left);
            TreeNode right = root.right;
            TreeNode left = root.left;
            tailOfLeft.right = right;
            root.right = root.left;
            root.left = null;
            // before returning, we must check whether tailOfRight is empty or not
            // cause we do not want to return a null tail, however, we know tail of
            // left is not null.
            if (tailOfRight == null) return tailOfLeft;
            return tailOfRight;
        }
    }

    // approach 2: iterative (O(1) space)
    // similar to Morris traversal

    public void flatten_iterative(TreeNode root) {

        while (root != null) {
            if (root.left != null) {
                TreeNode last = root.left;
                while (last.right != null) {
                    last = last.right;
                }
                last.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
}
