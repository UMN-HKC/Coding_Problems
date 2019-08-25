package Leetcode;

public class P0333_LargestBSTSubtree {

    // approach 1: post order traversal + bottom-up recursion
    // The basic idea is to use bottom-up approach. With top-down approach, we
    // still have to determine or calculate child status first, which increases
    // time complexity. So, what we will do is to use a Result class to store
    // information that we need, and do post order traversal and pass up the
    // result from children

    class Result {
        int max;
        int min;
        int size;
        boolean isValid;
        public Result(int max, int min, int size, boolean isValid) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.isValid = true;
        }
    }
    public int largestBSTSubtree(TreeNode root) {
        return helper(root).size;
    }
    public Result helper(TreeNode root) {
        if (root == null) return new Result(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true);
        Result lr = helper(root.left);
        Result rr = helper(root.right);
        // if the returned subtree is already not a valid binary search tree,
        // we just return its result to the caller without updating anything
        if (!lr.isValid || !rr.isValid || lr.max >= root.val || rr.min <= root.val) {
            return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE, Math.max(lr.size, rr.size), false);
        }
        return new Result(Math.max(root.val, rr.max), Math.min(root.val, lr.min), rr.size + lr.size + 1, true);
    }
}
