package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    // approach 1: recursion
    // 做这种题目，脑袋瓜子真的得十分清晰才行，这个index真的搞了大半个小时
    // The root node is the last element in the post order array, and the element to
    // the left of this element is the right node of the root node. We can utilize
    // these information to get the number of left nodes we have, with the help of
    // pre order array (find the position of root node in the pre order array, and then
    // find the position of the first right node in the pre order array, then it's easy
    // to get the number of left nodes)
    // Use map to pre store nodes' respective positions from post order array to
    // pre order array

    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        for (int i = 0; i < post.length; i++) {
            int root = post[i];
            for (int j = 0; j < pre.length; j++) {
                if (root == pre[j]) {
                    map.put(i, j);
                    break;
                }
            }
        }
        return buildTree(post, 0, post.length - 1);
    }
    public TreeNode buildTree(int[] post, int postS, int postE) {
        if (postS > postE) return null;
        TreeNode root = new TreeNode(post[postE]);
        if (postS == postE) return root;
        else {
            int rootPos = map.get(postE);
            int rightPos = map.get(postE - 1);
            int sizeOfLeft = rightPos - rootPos - 1;
            root.left = buildTree(post, postS, postS + sizeOfLeft - 1);
            root.right = buildTree(post,  postS + sizeOfLeft , postE - 1);
            return root;
        }
    }
}
