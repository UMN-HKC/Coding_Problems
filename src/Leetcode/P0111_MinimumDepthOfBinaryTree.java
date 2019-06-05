package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0111_MinimumDepthOfBinaryTree {

    // initial approach: dfs

    // time: O(V+E)
    // space: O(h)

    public int minDepth_dfs(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 1, Integer.MAX_VALUE);
    }
    public int dfs(TreeNode node, int cur, int min) {
        if (node == null) {
            return min;
        }
        if (node.left == null && node.right == null) {
            return Math.min(cur, min);
        }
        return Math.min(dfs(node.left, cur+1, min), dfs(node.right, cur+1, min));
    }

    // more concise
    public int minDepth_concise(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
    }

    // approach 2: bfs
    // for this problem, bfs should be more efficient for some extreme cases which would cause
    // dfs do a lot of extra work

    // time: O(V+E)
    // space: O(V)

    public int minDepth_bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (root.left == null && root.right == null) {
                    return depth;
                }
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
        }
        return depth;
    }
}
