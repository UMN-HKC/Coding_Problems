package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P0094_InorderTreeTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
