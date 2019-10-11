package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0662_MaximumWidthOfBinaryTree {

    // approach 1: bfs
    // The basic idea is to realize the parent and child node's position relationship:
    // left[id] = 2 * root[id]; right[id] = 2 * root[id] + 1
    // Then, we can use two queues: one to store nodes and the other one to store positions

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int max = 1;
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> posQ = new LinkedList<>();
        nodeQ.offer(root);
        posQ.offer(1);
        while (!nodeQ.isEmpty()) {
            // l is only set here (the leftmost node's position)
            int l = posQ.peek();
            // first init right position same as l, and then as
            // we traverse to the right, update r at the same time
            int r = l;
            int size = nodeQ.size();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodeQ.poll();
                r = posQ.poll();
                if (curNode.left != null) {
                    nodeQ.offer(curNode.left);
                    posQ.offer(r * 2);
                }
                if (curNode.right != null) {
                    nodeQ.offer(curNode.right);
                    posQ.offer(r * 2 + 1);
                }
            }
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}
