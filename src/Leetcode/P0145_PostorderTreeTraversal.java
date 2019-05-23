package Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class P0145_PostorderTreeTraversal {
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

    public List<Integer> postorderTraversal_2(TreeNode root) {
        Stack<TreeNode> s = new Stack();
        List<Integer> ans = new ArrayList<Integer>();
        TreeNode cur = root;

        while (cur != null || !s.empty()) {
            while (!isLeaf(cur)) {
                s.push(cur);
                cur = cur.left;
            }

            if (cur != null) ans.add(cur.val);

            while (!s.empty() && cur == s.peek().right) {
                cur = s.pop();
                ans.add(cur.val);
            }

            if (s.empty()) cur = null; else cur = s.peek().right;
        }

        return ans;
    }

    private boolean isLeaf(TreeNode r) {
        if (r == null) return true;
        return r.left == null && r.right == null;
    }
}
