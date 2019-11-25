package Leetcode;

public class P0993_CousinsInBinaryTree {

    // approach 1: dfs

    int xDepth = -1;
    int yDepth = -1;
    TreeNode xParent = null;
    TreeNode yParent = null;
    public boolean isCousins(TreeNode root, int x, int y) {
        find(null, root, x, y, 0);
        return xDepth == yDepth && xParent != yParent;
    }
    private void find(TreeNode parent, TreeNode node, int x, int y, int depth) {
        if (node == null) return;
        if (node.val == x) {
            xDepth = depth;
            xParent = parent;
        }
        else if (node.val == y) {
            yDepth = depth;
            yParent = parent;
        }
        else {
            find(node, node.left, x, y, depth + 1);
            find(node, node.right, x, y, depth + 1);
        }
    }
}
