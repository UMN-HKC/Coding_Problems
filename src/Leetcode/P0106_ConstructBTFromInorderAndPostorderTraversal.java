package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0106_ConstructBTFromInorderAndPostorderTraversal {

    // approach 1:
    // similar to build BT from inorder and preorder traversal
    // The basic idea is to realize that the last element in the postorder array
    // is the root, and use that info and go into inorder to find the index of
    // the root(which is already pre computed and stored in the hashmap), and then
    // all the element to the left of it will consist of left subtree and all
    // element to the right of it will consist of right subtree.

    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0 || inorder == null || inorder.length == 0) return null;
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(inorder, 0, inorder.length - 1, postorder, postorder.length - 1);
    }
    public TreeNode buildTreeHelper(int[] inorder, int s, int e, int[] postorder, int index) {
        if (s > e || index < 0) return null;

        int pos = map.get(postorder[index]);
        TreeNode root = new TreeNode(postorder[index]);
        root.left = buildTreeHelper(inorder, s, pos - 1, postorder, index - (e - pos) - 1);
        root.right = buildTreeHelper(inorder, pos + 1, e, postorder, index - 1);
        return root;
    }
}
