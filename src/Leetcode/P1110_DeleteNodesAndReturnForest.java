package Leetcode;
import java.util.*;

public class P1110_DeleteNodesAndReturnForest {

    // initial approach: post-order traversal

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int n : to_delete) {
            set.add(n);
        }
        helper(res, root, null, set);
        return res;
    }
    public TreeNode helper(List<TreeNode> res, TreeNode node, TreeNode parent, Set<Integer> set) {
        if (node == null) return null;
        TreeNode left = helper(res, node.left, node, set);
        TreeNode right = helper(res, node.right, node, set);
        if (set.contains(node.val)) {
            // add new generated forest to result
            if (left != null) res.add(left);
            if (right != null) res.add(right);
            return null;
        }
        else {
            // if its children are deleted, we need to clear this node's link to these children
            if (left == null) node.left = null;
            if (right == null) node.right = null;
            // if the current node is not to delete and it is the root node, we need to add it
            if (parent == null) {
                res.add(node);
            }
        }
        return node;
    }
}
