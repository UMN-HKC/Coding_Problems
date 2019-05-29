package Leetcode;

import java.util.*;

public class P0257_BinaryTreePath {

//    Initial solution: used dfs with backtracking. Since I used stringbuilder which is a mutable data
//    structure, will need to backtrack after dfs for the left child to recover to the previous state for
//    happening of dfs for right child.

//    time: O(V + E) because we traversed the whole tree
//    space: O(depth) for the stack space we used.

    public List<String> binaryTreePaths_dfs_with_backtracking(TreeNode root) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, root);
        return res;
    }
    public void dfs(List<String> res, StringBuilder sb, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            sb.append(Integer.toString(node.val));
            res.add(new String(sb));
            return;
        }
        sb.append(Integer.toString(node.val) + "->");
        int len = sb.length();
        dfs(res, sb, node.left);
        sb.setLength(len);
        dfs(res,sb, node.right);
        return;
    }

    //  approach 2: bfs with queues
    public List<String> binaryTreePaths_bfs(TreeNode root) {
        List<String> list=new ArrayList<String>();
        Queue<TreeNode> qNode=new LinkedList<TreeNode>();
        Queue<String> qStr=new LinkedList<String>();

        if (root==null) return list;
        qNode.add(root);
        qStr.add("");
        while(!qNode.isEmpty()) {
            TreeNode curNode=qNode.remove();
            String curStr=qStr.remove();

            if (curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
            if (curNode.left!=null) {
                qNode.add(curNode.left);
                qStr.add(curStr+curNode.val+"->");
            }
            if (curNode.right!=null) {
                qNode.add(curNode.right);
                qStr.add(curStr+curNode.val+"->");
            }
        }
        return list;
    }

    // approach 3: iterative dfs with stack
    public List<String> binaryTreePaths_dfs_with_stack(TreeNode root) {
        List<String> list=new ArrayList<String>();
        Stack<TreeNode> sNode=new Stack<TreeNode>();
        Stack<String> sStr=new Stack<String>();

        if(root==null) return list;
        sNode.push(root);
        sStr.push("");
        while(!sNode.isEmpty()) {
            TreeNode curNode=sNode.pop();
            String curStr=sStr.pop();

            if(curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
            if(curNode.left!=null) {
                sNode.push(curNode.left);
                sStr.push(curStr+curNode.val+"->");
            }
            if(curNode.right!=null) {
                sNode.push(curNode.right);
                sStr.push(curStr+curNode.val+"->");
            }
        }
        return list;
    }
}
