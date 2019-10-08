package Leetcode;
import java.util.*;

public class P0652_FindDuplicateSubtrees {

    // approach 1: post-order traversal

    // time: O(n^2)
    // space: O(n^2)

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        serialize(res, map, root);
        return res;
    }
    public String serialize(List<TreeNode> res, Map<String, Integer> map, TreeNode cur) {
        if (cur == null) return "#";
        String path = String.valueOf(cur.val) + "," + serialize(res, map, cur.left) + serialize(res, map, cur.right);
        map.put(path, map.getOrDefault(path, 0) + 1);
        if (map.get(path) == 2) {
            res.add(cur);
        }
        return path;
    }
}
