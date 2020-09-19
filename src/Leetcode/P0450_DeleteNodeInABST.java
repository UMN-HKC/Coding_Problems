package Leetcode;

public class P0450_DeleteNodeInABST {

    // approach 1: iterative

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            return connect(null, root);
        }
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val < key) {
                pre = cur;
                cur = cur.right;
            }
            else if (cur.val > key){
                pre = cur;
                cur = cur.left;
            }
            else {
                connect(pre, cur);
                break;
            }
        }
        return root;
    }
    private TreeNode connect(TreeNode pre, TreeNode cur) {
        TreeNode rightSmallestNode = cur.right;
        while (rightSmallestNode != null && rightSmallestNode.left != null) {
            rightSmallestNode = rightSmallestNode.left;
        }
        // we are removing root node;
        if (pre == null) {
            if (rightSmallestNode == null) {
                TreeNode res = cur.left;
                cur.left = null;
                return res;
            }
            else {
                TreeNode res = cur.right;
                rightSmallestNode.left = cur.left;
                cur.left = null;
                cur.right = null;
                return res;
            }
        }
        else {  // removing non-root node

            TreeNode connected = null;
            TreeNode remove = cur;
            if (rightSmallestNode == null) {
                connected = cur.left;
                cur.left = null;
            }
            else {
                connected = cur.right;
                cur.right = null;
                rightSmallestNode.left = cur.left;
                cur.left = null;
            }
            if (pre.val > remove.val) {
                pre.left = connected;
            }
            else {
                pre.right = connected;
            }
            return pre;
        }
    }
}
