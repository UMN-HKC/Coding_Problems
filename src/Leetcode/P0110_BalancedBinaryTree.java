package Leetcode;

public class P0110_BalancedBinaryTree {

    // initial approach: postorder tree traversal / dfs
    // the key idea is to use -1 to indicate that we have already found an unbalanced subtree
    // so that we could early terminate other subtree searching

    // time: O(V+E)
    // space: O(h)

    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return getHeight(root) == -1 ? false : true;
    }
    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        if (leftHeight == -1)  return -1;
        int rightHeight = getHeight(node.right);
        if (rightHeight == -1)  return -1;
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
