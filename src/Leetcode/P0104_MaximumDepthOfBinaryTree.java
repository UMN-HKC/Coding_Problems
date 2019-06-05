package Leetcode;

public class P0104_MaximumDepthOfBinaryTree {

    // initial approach: dfs

    // time: O(V+E)
    // space: O(depth)

    public int maxDepth_dfs(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0, Integer.MIN_VALUE);
    }
    public int dfs(TreeNode node, int cur, int max) {
        if (node == null) {
            return max;
        }
        cur++;
        max = Math.max(cur, max);
        return Math.max(dfs(node.left, cur, max), dfs(node.right, cur, max));
    }

    // more concise

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
