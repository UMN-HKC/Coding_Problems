package Leetcode;

public class P0235_LowestCommonAncestorOfBST {

    // initial approach: basic idea is that once we find a node's value that's between
    // two provided nodes' value, the node is what we are looking for

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) return lowestCommonAncestor(root, q, p);
        while (!(root.val >= p.val && root.val <= q.val)) {
            if (root.val > q.val) {
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
        return root;
    }
}
