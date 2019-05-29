package Leetcode;

import java.util.Stack;

public class P0112_PathSum {
    // initial solution with dfs approach

    // time: O(V+E)
    // space O(h)
    public boolean hasPathSum_recursive(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && (sum - root.val) == 0) {
            return true;
        }
        return hasPathSum_recursive(root.left, sum - root.val) || hasPathSum_recursive(root.right, sum - root.val);
    }

    // approach 2: iterative postorder approach. Modified from postorder traversal
    public boolean hasPathSum_postorder(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            if (root != null) {
                sum -= root.val;
                stack.push(root);
                root = root.left;
            }
            else {
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    // this is to check if the node at the top of the stack is a leaf node
                    // and if it is, return true if we have accumulated the target sum
                    if (stack.peek().left == null && sum == 0) return true;
                    // each time when we pop, we need to add the node's value back(backtrack)
                    temp = stack.pop();
                    sum += temp.val;
                    while (!stack.empty() && stack.peek().right == temp) {
                        temp = stack.pop();
                        sum += temp.val;
                    }
                }
                else {
                    root = temp;
                }
            }
        }
        return false;
    }

}
