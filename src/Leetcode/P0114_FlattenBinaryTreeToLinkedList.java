package Leetcode;

public class P0114_FlattenBinaryTreeToLinkedList {


    // approach 1: recursion, dfs

    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            flatten(root.left);
            TreeNode leftTail = root.left;
            while (leftTail.right != null) {
                leftTail = leftTail.right;
            }
            TreeNode right = root.right;
            root.right = root.left;
            leftTail.right = right;
            root.left = null;
        }
        flatten(root.right);
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
