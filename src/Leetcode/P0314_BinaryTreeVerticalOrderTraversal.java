package Leetcode;
import java.util.*;

public class P0314_BinaryTreeVerticalOrderTraversal {

    // approach 1: bfs

    // The idea is to use two queues to both store nodes and respective nodes'
    // horizontal positions relative to 0. We do breadth first search to traverse
    // the tree. Also, we will use 2 variables to store the leftmost and rightmost
    // horizontal positions so that we could retrieve values later.

    Map<Integer, List<Integer>> map = new HashMap<>();
    int leftMost = 0;
    int rightMost = 0;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<Integer> q = new LinkedList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        q.offer(0);
        nodes.offer(root);

        while (!nodes.isEmpty()) {
            int pos = q.poll();
            root = nodes.poll();
            if (!map.containsKey(pos)) {
                map.put(pos, new ArrayList<>());
            }
            map.get(pos).add(root.val);
            if (root.left != null) {
                leftMost = Math.min(leftMost, pos - 1);
                q.offer(pos - 1);
                nodes.offer(root.left);
            }
            if (root.right != null) {
                rightMost = Math.max(rightMost, pos + 1);
                q.offer(pos + 1);
                nodes.offer(root.right);
            }
        }
        while (leftMost <= rightMost) {
            res.add(map.get(leftMost++));
        }
        return res;
    }
}
