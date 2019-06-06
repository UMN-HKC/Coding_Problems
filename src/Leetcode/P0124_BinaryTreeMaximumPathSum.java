package Leetcode;

public class P0124_BinaryTreeMaximumPathSum {

    // idea borrowed from discussion board

    // time: O(V+E)
    // space: O(h)

    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        if (root == null) return max[0];
        max[0] = Integer.MIN_VALUE;
        postOrder(root, max);
        return max[0];
    }
    public int postOrder(TreeNode node, int[] max) {
        if (node == null) {
            return 0;
        }
        // compare to 0 to make comparison and later operation more concise
        // so we don't have to check if l or r is negative or not, we
        // simply add l and r both
        int l = Math.max(0, postOrder(node.left, max));
        int r = Math.max(0, postOrder(node.right, max));
        max[0] = Math.max(max[0], l + r + node.val);

        return Math.max(l, r) + node.val;
    }
}
