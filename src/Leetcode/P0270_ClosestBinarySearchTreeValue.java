package Leetcode;

public class P0270_ClosestBinarySearchTreeValue {

    // approach 1: keep a result value which keeps the closest value globally and
    // update the result value on the go

    public int closestValue(TreeNode root, double target) {
        TreeNode itr = root;
        int res = root.val;
        while (itr != null) {
            res = Math.abs(target - res) < Math.abs(target - itr.val) ? res : itr.val;
            if (itr.val > target) {
                itr = itr.left;
            }
            else {
                itr = itr.right;
            }
        }
        return res;
    }
}
