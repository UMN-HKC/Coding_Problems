package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0437_PathSumIII {

    // note that we need to read the problem carefully, since the path must go downwards
    // (traveling only from parent nodes to child nodes), which means it will be a linear path
    // without branching

    // approach 1: brute force
    // https://leetcode.com/problems/path-sum-iii/discuss/91889/Simple-Java-DFS

    // The critical part of the problem is to understand what exactly each recursion function
    // does.
    // pathSum()'s task is to calculate the total number of path that sums to sum, when
    // the current node is the root node in the tree.
    // countPathSum()'s task is to calculate the total number of path that sums to sum, when
    // the current node is the starting node in the path

    // time: O(n^2)
    // pathSum(): O(n) because it basically visit each node
    // countPathSum: O(logn) or O(n). it will take range from O(n) (single sided tree) to
    // O(logn)(balanced tree). This is due to here we are get all the paths from a given node. The length of path is proportional to the tree height.

    // space: O(n)

    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return countPathSum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    public int countPathSum(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) +
                countPathSum(node.left, sum - node.val) +
                countPathSum(node.right, sum - node.val);
    }

    // approach 2: dfs + backtracking + prefix sum as memoization
    // countPathSum()'s task is that for the current node, calculate the total number of path
    // that ends with the current node whose path sum equals target. Then, it makes recursive
    // class to its left and right children. Adding their result and pass upwards to its parent

    // time: O(n)
    // space: O(n)

    public int pathSum_2(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return countPathSum(map, root, 0, sum);
    }
    public int countPathSum(Map<Integer, Integer> map, TreeNode node, int sum, int target) {
        if (node == null) return 0;
        int res = 0;
        sum += node.val;
        // get the total number of path that ending with the current node whose sum equals target
        if (map.containsKey(sum - target)) {
            res += map.get(sum - target);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        // get the total number of path starting from the current node and ending somewhere
        // down the path whose sum equals target
        res += countPathSum(map, node.left, sum, target) + countPathSum(map, node.right, sum, target);
        map.put(sum, map.get(sum) - 1);
        return res;
    }
}
