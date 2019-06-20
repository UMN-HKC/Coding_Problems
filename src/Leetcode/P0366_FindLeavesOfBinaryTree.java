package Leetcode;
import java.util.*;

public class P0366_FindLeavesOfBinaryTree {

    // initial approach: post order
    // mark each added leaves their respective level(height), and when returning, return its height to its
    // parent. Thus, all leaves are added according to their height.

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }
    public int dfs(List<List<Integer>> res, TreeNode node) {
        if (node == null) {
            return -1;
        }
        int left = dfs(res, node.left);
        int right = dfs(res, node.right);
        int curLevel = Math.max(left, right) + 1;
        if (res.size() <= curLevel) {
            List<Integer> list = new ArrayList<>();
            list.add(node.val);
            res.add(list);
        }
        else {
            res.get(curLevel).add(node.val);
        }
        node.left = null;
        node.right = null;
        return curLevel;
    }
}
