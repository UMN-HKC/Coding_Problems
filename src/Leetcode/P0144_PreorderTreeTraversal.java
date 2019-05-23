package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P0144_PreorderTreeTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            if (root != null) {
                res.add(root.val);
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop().right;
            }
        }
        return res;
    }
}
