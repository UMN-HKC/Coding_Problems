package Leetcode;

public class P1315_SumOfNodesWithEvenValuedGrandparent {

    // approach 1: dfs
    // countMyGrandChildren(): calculates the sum of the grandchildren values if self is even
    // The basic idea is to pass down parent and grandparent so that children nodes have access
    // to them. Each child will also call countMyGrandChildren().

    // time: O(n)
    // space: O(logn)

    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) return 0;
        return countMyGrandChildren(root, null, null);
    }
    private int countMyGrandChildren(TreeNode cur, TreeNode parent, TreeNode grandParent) {
        if (cur == null) return 0;
        int res = (grandParent != null && grandParent.val % 2 == 0) ? cur.val : 0;
        res += countMyGrandChildren(cur.left, cur, parent) + countMyGrandChildren(cur.right, cur, parent);
        return res;
    }
}
