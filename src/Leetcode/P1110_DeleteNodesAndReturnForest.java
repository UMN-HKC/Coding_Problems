package Leetcode;
import java.util.*;

public class P1110_DeleteNodesAndReturnForest {

    // approach 1: post-order traversal
    // applying post-order traversal, we will assume root node's children have been
    // updated, and we need to take care of root node's pointers to its left and right
    // children. Also, we will pass current node's reference to recursive calls on children.
    // If root node is to be deleted, we need to add its left and right children
    // to the forest list if they are not null. Otherwise, we check on its children
    // and parent node's status, and update those pointers accordingly

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int n : to_delete) {
            set.add(n);
        }
        helper(res, root, null, set);
        return res;
    }
    public TreeNode helper(List<TreeNode> res, TreeNode node, TreeNode parent, Set<Integer> set) {
        if (node == null) return null;
        TreeNode left = helper(res, node.left, node, set);
        TreeNode right = helper(res, node.right, node, set);
        if (set.contains(node.val)) {
            // add new generated forest to result
            if (left != null) res.add(left);
            if (right != null) res.add(right);
            // return null to indicate child is deleted
            return null;
        }
        else {
            // if its children are deleted, we need to clear this node's link to these children
            if (left == null) node.left = null;
            if (right == null) node.right = null;
            // if the current node is not to delete and it is the root node, we need to add it
            if (parent == null) {
                res.add(node);
            }
        }
        return node;
    }

    // approach 2: preorder
    // first deal with the root node, and recurse on its left and right child
    // two things need to be taken care of:
    // - whether parent has been deleted or not
    // - before recursing on child nodes, check if we need to remove parents' link to them first

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> delete = new HashSet<>();
        for (int num : to_delete) {
            delete.add(num);
        }
        dfs(res, delete, root, true);
        return res;
    }
    public void dfs(List<TreeNode> res, Set<Integer> delete, TreeNode cur, boolean parentDeleted) {
        if (cur == null) return;
        TreeNode leftChild, rightChild;
        if (delete.contains(cur.val)) {
            delete.remove(cur.val);
            leftChild = cur.left;
            rightChild = cur.right;
            cur.left = null;
            cur.right = null;
            parentDeleted = true;
        }
        else {
            if (parentDeleted) {
                res.add(cur);
                parentDeleted = false;
            }
            leftChild = cur.left;
            rightChild = cur.right;
            // if children will be deleted, we need to cut the link to them before recursion
            if (leftChild != null && delete.contains(leftChild.val)) {
                cur.left = null;
            }
            if (rightChild != null && delete.contains(rightChild.val)) {
                cur.right = null;
            }
        }
        dfs(res, delete, leftChild, parentDeleted);
        dfs(res, delete, rightChild, parentDeleted);
    }
}
