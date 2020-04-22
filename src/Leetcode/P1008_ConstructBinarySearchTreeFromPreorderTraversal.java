package Leetcode;

public class P1008_ConstructBinarySearchTreeFromPreorderTraversal {

    // approach 1:

    // time: O(n^2)
    // space: O(n)

    public TreeNode bstFromPreorder(int[] preorder) {
        return buildTree(preorder, 0, preorder.length - 1);
    }
    private TreeNode buildTree(int[] preorder, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(preorder[l]);
        int rightStartIdx = l + 1;
        while (rightStartIdx <= r && preorder[rightStartIdx] < preorder[l]) {
            rightStartIdx++;
        }
        root.left = buildTree(preorder, l + 1, rightStartIdx - 1);
        root.right = buildTree(preorder, rightStartIdx, r);
        return root;
    }

    // approach 2:
    // The basic idea is to keep a global index to traverse the preorder
    // array and recursively build up the tree with the help of min and
    // max 

    // time: O(n)
    // space: O(n)

    int i = 0;
    public TreeNode bstFromPreorder_2(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private TreeNode helper(int[] preorder, int min, int max) {
        if (i >= preorder.length) return null;

        if (preorder[i] < min || preorder[i] > max) return null;
        TreeNode root = new TreeNode(preorder[i]);
        i++;
        root.left = helper(preorder, min, root.val);
        root.right = helper(preorder, root.val, max);
        return root;
    }
}
