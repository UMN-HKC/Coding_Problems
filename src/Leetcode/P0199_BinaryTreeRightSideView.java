package Leetcode;
import java.util.*;


public class P0199_BinaryTreeRightSideView {

    // approach 1: bfs
    // The idea is to do a level order traversal and in each level, we store the rightmost
    // node's value to our result

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (i == size - 1) {
                    res.add(root.val);
                }
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
        }
        return res;
    }

    // approach 2: modified preorder traversal

    // time: O(n) basically we still visit each node

    // The key idea is to do a modified pre order traversal where we visit the parent and
    // then its right child, because we want the right side view. Note that we only add the
    // view at current level when it hasn't been added(this is done by checking if result size
    // is equal to current depth)

    public List<Integer> rightSideView_2(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        // The following 2 lines of codes are the essence of this approach
        // it only adds the value when the view at current level hasn't been added
        if(currDepth == result.size()){
            result.add(curr.val);
        }
        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }
}
