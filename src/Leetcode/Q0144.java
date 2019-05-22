package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Q0144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return res;
        }
        stack.push(root);
        while (!stack.empty()) {
            TreeNode temp = stack.pop();
            while (temp != null) {
                if (temp.right != null) {
                    stack.push(temp.right);
                }
                res.add(temp.val);
                temp = temp.left;
            }
        }
        return res;
    }
}
