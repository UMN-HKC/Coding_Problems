package Leetcode;

public class P0236_LowestCommonAncestorOfBT {

    // couldn't figure out initially, idea borrowed from discussion board
    // super concise and to the point

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // deal with null and passing result node to parent
        // 'root == null' simply 'nulls' all subtrees that are not an ancestor of any target node
        // from leaf bubbling up, all non-ancestor nodes are ignored and null gets returnd to parent
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : (right == null ? left : root);
    }

    // optimized time complexity

    public TreeNode lowestCommonAncestor_optimized(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // by checking that if left is not null, we know that left must be one
        // of the target nodes or the lowest common ancestor. So by further checking
        // if the left node's value differs from p and q, we do not need to check
        // our current node's right subtree since left is the lowest common ancestor.
        if (left != null && left.val != p.val && left.val != q.val) return left;
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : (right == null ? left : root);
    }
}
