package Leetcode;
import java.util.*;

public class P0337_HouseRobberIII {

    // couldn't figure out initially
    // idea borrowed from discussion board
    // basically, need to be open to address grandchildren of the parent node
    // when we want to include the parent node's value, we will combine that with
    // the grandchildren. If we do not choose the parent node's value, we will
    // choose its two children.

    public int rob(TreeNode root) {
        return search(root, new HashMap<>());
    }
    public int search(TreeNode node, Map<TreeNode, Integer> map) {
        if (node == null) return 0;

        if (map.containsKey(node)) {
            return map.get(node);
        }

        int includeCur = node.val;
        if (node.left != null) {
            includeCur += search(node.left.left, map) + search(node.left.right, map);
        }
        if (node.right != null) {
            includeCur += search(node.right.left, map) + search(node.right.right, map);
        }
        int res = Math.max(includeCur, search(node.left, map) + search(node.right, map));
        map.put(node, res);
        return res;
    }
}
