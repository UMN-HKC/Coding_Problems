package Leetcode;
import java.util.*;

public class P0098_ValidateBST {

    // initial approach: recursive approach
    // pass down the minimum and maximum value for each child to check if they violate the rule
    // remember to pass down long value to avoid edge cases like Integer.MAX_VALUE

    public boolean isValidBST_recursive(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean helper(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val >= max || node.val <= min) return false;
        boolean isLeftBST = helper(node.left, min, node.val);
        boolean isRightBST = helper(node.right, node.val, max);
        return isLeftBST && isRightBST;
    }

    // approach 2: iterative
    // the main idea is to apply inorder traversal since it is a BST. Keep a prev node
    // pointer when popping out from the stack instead of tracking the prev pointer
    // when pushing into stack
    // Always reduce a functionality to a minimized code block, when we keep track of prev
    // pointer when coming down the BST and checking at the same time, it will return wrong
    // answer because when prev pointer stops at leaf node and root node points back
    // to this leaf node when pops from stack, prev and root node are actually the same

    public boolean isValidBST_iterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (!stack.empty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                // check when pop()
                if (prev != null && prev.val >= root.val) return false;
                prev = root;
                root = root.right;
            }
        }
        return true;
    }
}
