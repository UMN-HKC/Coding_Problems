package Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P0113_PathSumII {

    // initial approach: recursive solution with backtracking

    // time: O(V+E)
    // space: O(h + res)
    public List<List<Integer>> pathSum_recursive(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        dfs(res, list, root, sum);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        if (node.left == null && node.right == null && (sum - node.val == 0)) {
            res.add(new ArrayList(list));
            list.remove(list.size() - 1);
            return;
        }
        else {
            dfs(res, list, node.left, sum - node.val);
            dfs(res, list, node.right, sum - node.val);
            list.remove(list.size() - 1);
        }
        return;
    }

    // approach 2: iterative postorder
    // modified directly from postorder traversal
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (root != null || !stack.empty()) {
            if (root != null) {
                sum -= root.val;
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            else {
                TreeNode temp = stack.peek().right;
                if (temp == null) {
                    if (stack.peek().left == null && sum == 0) {
                        res.add(new ArrayList<>(list));
                    }
                    temp = stack.pop();
                    // backtrack
                    list.remove(list.size() - 1);
                    sum += temp.val;
                    while (!stack.empty() && stack.peek().right == temp) {
                        temp = stack.pop();
                        list.remove(list.size() - 1);
                        sum += temp.val;
                    }
                }
                else {
                    root = temp;
                }
            }
        }
        return res;
    }
}
