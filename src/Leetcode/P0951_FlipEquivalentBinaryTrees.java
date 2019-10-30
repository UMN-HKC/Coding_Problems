package Leetcode;

public class P0951_FlipEquivalentBinaryTrees {

    // approach 1: recursion

    // The basic idea is that if two trees are flip equivalent, their root nodes' values should
    // be the same, and then either their respective left and right trees are flip equivalent
    // or their left and right, right and left should be flip equivalent

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) return false;
        if (root1.val == root2.val) {
            return (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left)) ||
                    (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right));
        }
        else {
            return false;
        }
    }
}
