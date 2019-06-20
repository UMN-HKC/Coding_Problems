package Leetcode;
import java.util.*;

public class P0103_BinaryTreeZigzagLevelOrderTraversal {

    // initial approach: level order traversal
    // the trick here is to not manipulate the sequence of adding children to our queue
    // but to manipulate the place to add value to the list (whether front or back)
    // and use linkedlist instead of arraylist to improve performance

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        int rotate = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if (rotate == 1) {
                    list.add(root.val);
                }
                else {
                    list.add(0, root.val);
                }
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            res.add(list);
            rotate = rotate == 1 ? -1 : 1;
        }
        return res;
    }
}
