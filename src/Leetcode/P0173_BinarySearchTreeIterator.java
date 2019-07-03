package Leetcode;
import java.util.*;

public class P0173_BinarySearchTreeIterator {

    // initial approach: use stack to do inorder traversal, since it is bst,
    // which will result in increasing value, and queue to store correct inorder order
    // this approach takes O(n) space will does not meet question's requirement

    Queue<TreeNode> queue = new LinkedList<>();
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
                queue.offer(root);
                root = root.right;
            }
        }
    }

    /** @return the next smallest number */
    public int next() {
        return queue.poll().val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    // approach 2: in-progress inorder traversal

    // use O(h) space and do inorder traversal in need
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        push(root);
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode next = stack.pop();
        if (next.right != null) {
            push(next.right);
        }
        return next.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }
    public void push(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
