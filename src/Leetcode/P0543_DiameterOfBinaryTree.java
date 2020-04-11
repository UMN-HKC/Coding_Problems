package Leetcode;

public class P0543_DiameterOfBinaryTree {

    // approach 1: post order traversal
    // the basic idea is that use a helper function to calculate longest depth
    // starting from current node, while inside helper function, update global
    // diameter as needed

    // time: O(n)
    // space: O(n)


    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return diameter;
    }
    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (leftDepth + rightDepth > diameter) {
            diameter = leftDepth + rightDepth;
        }
        return 1 + Math.max(leftDepth, rightDepth);
    }
}
