package Leetcode;

public class P0108_ConvertSortedArrayToBST {

    // recursive approach
    // be careful that when make recursive calls, boundary should be l
    // and r instead of 0 and nums.length - 1

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) return null;
        return helper(nums, 0, nums.length - 1);
    }
    public TreeNode helper(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        else {
            int mid = (l + r) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = helper(nums, l, mid - 1);
            root.right = helper(nums, mid + 1 , r);
            return root;
        }
    }
}
