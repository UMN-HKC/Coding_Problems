package Leetcode;
import java.util.*;

public class P0230_KthSmallestElementInBST {

    // initial approach: inorder traversal
    // keep a counter

    // time: O(n)
    // space: O(n)

    // if we could modify tree's structure, we can keep track of number of nodes in
    // node's leftsubtree when we initially build the tree and then searching for
    // kth node would be O(k) time complexity

    // we could also use morris traversal to save space

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int cnt = 0;
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                cnt++;
                if (cnt == k) return root.val;
                root = root.right;
            }

        }
        return -1;
    }
}
