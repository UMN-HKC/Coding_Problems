package Leetcode;

public class P0156_BinaryTreeUpsideDown {

    // approach 1: recursive

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            // when root.left is null, root.right must be null according to the rule
            return root;
        }
        // apply recursive call to our root's left node and the returned
        // node is the new root node of the current stack
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}
