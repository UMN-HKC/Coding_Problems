package Leetcode;
import java.util.*;

public class P0285_InorderSuccessorInBST {

    // initial approach: naive approach inorder iterative traversal

    // time: O(n)
    // space: O(n)

    public TreeNode inorderSuccessor_naive(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                if (prev != null && prev == p) {
                    return root;
                }
                prev = root;
                root = root.right;
            }
        }
        return null;
    }

    // approach 2: directly traverse the tree and keep a suc pointer to keep track of
    // potential successor.

    // time: O(n) for worst case, O(logn) for average
    // space: O(1)

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode suc = null;
        while (root != null) {
            if (root.val > p.val) {
                suc = root;
                root = root.left;
            }
            else {
                root = root.right;
            }
        }
        return suc;
    }
}
