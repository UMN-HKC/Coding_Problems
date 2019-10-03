package Leetcode;

import java.util.*;

public class P0987_VerticalOrderTraversalOfABinaryTree {

    // approach 1: bfs + map

    // the problem is similar to the other problem which also asks for the vertical order
    // traversal, but this question requires ordering within the same vertical line.
    // Basically, If two nodes have the same position
    // - check the layer, the node on higher level(close to root) goes first
    // - if they also in the same level, order from small value to large value
    // Note that for each node we visit, we map node to its x position and y(level) position
    // and when we map x position to its respective nodes, we use priority queue to store
    // those nodes so that we could maintain the order in our favor

    int left = Integer.MAX_VALUE;
    int right = Integer.MIN_VALUE;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, PriorityQueue<int[]>> lineMap = new HashMap<>();
        Map<TreeNode, int[]> nodeMap = new HashMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // map to x pos and level
        nodeMap.put(root, new int[]{0, 0});

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int[] info = nodeMap.get(node);
            int curX = info[0];
            int level = info[1];
            // put current node's info to our line map
            if (!lineMap.containsKey(curX)) {
                lineMap.put(curX,  new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));
            }
            lineMap.get(curX).offer(new int[]{node.val, level});
            // update left and right boundary
            left = Math.min(left, curX);
            right = Math.max(right, curX);

            if (node.left != null) {
                nodeMap.put(node.left, new int[]{curX - 1, level + 1});
                q.offer(node.left);
            }
            if (node.right != null) {
                nodeMap.put(node.right, new int[]{curX + 1, level + 1});
                q.offer(node.right);
            }
        }
        for (int i = left; i <= right; i++) {
            PriorityQueue<int[]> pqList = lineMap.get(i);
            List<Integer> list = new ArrayList<>();
            while (!pqList.isEmpty()) {
                list.add(pqList.poll()[0]);
            }
            res.add(list);
        }
        return res;
    }
}
