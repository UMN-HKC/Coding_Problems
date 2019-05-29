package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class P0145_PostorderTreeTraversal {

    // this is kind of cheating to solve the problem with reversed preorder traversal
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            if (root != null) {
                res.addFirst(root.val);
                stack.push(root);
                root = root.right;
            }
            else {
                root = stack.pop().left;
            }
        }
        return res;
    }

    // iterative postorder traversal
    public List<Integer> postorderTraversal_iterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()) {
            // all exploration of left subtree is done in here
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                TreeNode temp = stack.peek().right;
                // if temp is null, it means we have finished visiting the right subtree
                // so we can visit the node at the top of the stack
                if (temp == null) {
                    temp = stack.pop();
                    res.add(temp.val);
                    // iteratively check if temp is the right child of the node at the current top of the
                    // stack. If it is, it means we can also visit that node since we already visited temp
                    while (!stack.empty() && temp == stack.peek().right) {
                        temp = stack.pop();
                        res.add(temp.val);
                    }
                }
                else {
                    // temp is not null which means we need to explore right subtree,
                    // so we will reassign root pointer to temp for next iteration
                    root = temp;
                }
            }
        }
        return res;
    }
}
