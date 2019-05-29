package Leetcode;

import java.util.Stack;

public class P0129_SumRootToLeafNumber {
    // initial solution: recursive approach

    // time: O(E + V)
    // space O(h)

    public int sumNumbers_recursive(TreeNode root) {
        return dfs(root, 0);
    }
    public int dfs(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return sum * 10 + node.val;
        }
        return dfs(node.left, sum * 10 + node.val) + dfs(node.right, sum * 10 + node.val);
    }

    // approach 2: iterative. It is in fact a preorder traversal using stack
    public int sumNumbers_iterative(TreeNode root) {
        if(root == null){
            return 0;
        }
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<String> nodePath = new Stack<>();
        nodeStack.push(root);
        nodePath.push(""+root.val);
        int runningSum = 0;
        while(!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            String currentPath = nodePath.pop();
            if(node.right != null){
                nodeStack.push(node.right);
                nodePath.push(currentPath + (""+node.right.val));
            }
            if(node.left != null){
                nodeStack.push(node.left);
                nodePath.push(currentPath+ (""+ node.left.val) );
            }
            if( node.left == null && node.right == null){
                runningSum = runningSum + Integer.valueOf(currentPath);
            }

        }
        return runningSum;

    }
}
