package Leetcode;

public class P0298_BinaryTreeLongestConsecutiveSequence {

    // was thinking about the same idea, but failed to incorporate updating max
    // The idea is simple dfs, and passing along the max value, each time we find target
    // we update max. We could also use a global max instead

    // time: O(V+E)
    // space: O(h)

    public int longestConsecutive(TreeNode root) {
        if (root == null)   return 0;
        return dfs(root, 0, root.val, 0);
    }

    public int dfs(TreeNode node, int len, int target, int max) {
        if (node == null) {
            return max;
        }
        if (node.val == target) {
            len++;
            max = Math.max(len, max);
        }
        else {
            len = 1;
        }

        return Math.max(dfs(node.left, len, node.val + 1, max), dfs(node.right, len, node.val + 1, max));
    }
}
