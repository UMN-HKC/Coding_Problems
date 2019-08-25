package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0105_ConstructBTFromPreorderAndInorderTraversal {

    // approach 1:
    // The key idea is to realize that the first element in the preorder array
    // is the root, so we can go to inorder array to find this value, and all
    // the elements to the left of this value consisting left subtree and all
    // the element to the right of this value consisting right subtree.

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        return buildTreeHelper(preorder, 0, inorder, 0, inorder.length - 1);

    }
    public TreeNode buildTreeHelper(int[] preorder, int index, int[] inorder, int s, int e) {
        if (index >= preorder.length || s > e) return null;

        int rootVal = preorder[index];
        TreeNode root = new TreeNode(rootVal);
        int pos = -1;
        for (int i = s; i <= e; i++) {
            if (inorder[i] == rootVal) {
                pos = i;
                break;
            }
        }
        root.left = buildTreeHelper(preorder, index+1, inorder, s, pos-1);
        root.right = buildTreeHelper(preorder, index+ pos-s+1 , inorder, pos+1, e);
        return root;
    }

    // approach 2:
    // leverage hash map to pre store inorder positions and reduce time complexity

    Map<Integer, Integer> positions;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) return null;
        positions = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            positions.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, 0, inorder, 0, inorder.length - 1);

    }
    public TreeNode buildTreeHelper(int[] preorder, int index, int[] inorder, int s, int e) {
        if (index >= preorder.length || s > e) return null;

        int rootVal = preorder[index];
        TreeNode root = new TreeNode(rootVal);
        int pos = positions.get(rootVal);
        root.left = buildTreeHelper(preorder, index+1, inorder, s, pos-1);
        root.right = buildTreeHelper(preorder, index+ pos-s+1 , inorder, pos+1, e);
        return root;
    }
}
