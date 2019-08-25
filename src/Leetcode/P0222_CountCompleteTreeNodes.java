package Leetcode;

public class P0222_CountCompleteTreeNodes {

    // approach 1: time O(n)

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // approach 2: time O((logn)^2)
    // use complete binary tree's property: a complete binary
    // tree of height h has (2^h - 1) number of nodes.
    // The basic idea is that we first find the height of the whole binary tree
    // and then we find the height of the root's right subtree, if the right subtree
    // height is exactly 1 less than the height of the whole tree, it means that
    // the last node in the last row of the tree is in the right subtree. So the
    // total number of nodes is the number of nodes of the root's left complete tree(1 << h-1)
    // and the recursively calculated number of nodes in the right subtree and 1 root node.

    // If the height of the right subtree is not 1 less than the height of the
    // whole tree, then the last node of the last row in the tree is in the left subtree
    // so the total number of nodes is the number of nodes of the root's right subtree(1 << h-2)
    // and recursively calculated number of nodes in the left subtree and 1 root node.

    public int findHeight(TreeNode root) {
        int cnt = 0;
        while (root != null) {
            root = root.left;
            cnt++;
        }
        return cnt;
    }
    public int countNodes(TreeNode root) {
        int height = findHeight(root);
        if (height == 0) return 0;
        int rightHeight = findHeight(root.right);
        if (rightHeight == height - 1) {
            return (1 << (height-1)) + countNodes(root.right);
        }
        else {
            return (1 << (height-2)) + countNodes(root.left);
        }
    }
}
