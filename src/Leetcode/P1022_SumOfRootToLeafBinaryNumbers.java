package Leetcode;

public class P1022_SumOfRootToLeafBinaryNumbers {

    // approach 1: dfs

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        sum = sum * 2 + root.val;

        if (root.left == null && root.right == null) {
            return sum;
        }
        else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    // approach 2: Morris
    // refer to leetcode official answer

    public int sumRootToLeaf_2(TreeNode root) {
        int res = 0;
        int curNum = 0;
        while (root != null) {
            if (root.left != null) {
                // Predecessor node is one step to the left
                // and then to the right till you can.
                int depth = 1;
                TreeNode itr = root.left;
                while (itr.right != null && itr.right != root) {
                    itr = itr.right;
                    depth++;
                }
                // set link and continue exploring the left subtree
                if (itr.right == null) {
                    itr.right = root;
                    curNum = (curNum << 1) | root.val;
                    root = root.left;
                }
                // Break the link
                else {
                    // If you're on the leaf, update the sum
                    if (itr.left == null) {
                        res += curNum;
                    }
                    // This part of tree is explored, backtrack
                    curNum >>= depth;
                    itr.right = null;
                    root = root.right;
                }
            }
            // If there is no left child
            // then just go right.
            else {
                curNum = (curNum << 1) | root.val;
                // if you're on the leaf, update the sum
                if (root.right == null) {
                    res += curNum;
                }
                root = root.right;
            }
        }
        return res;
    }
}
