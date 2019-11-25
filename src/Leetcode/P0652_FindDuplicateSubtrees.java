package Leetcode;
import java.util.*;

public class P0652_FindDuplicateSubtrees {

    // approach 1: post-order traversal
    // The basic idea is to serialize the tree and once we find a serialized string that
    // appeared twice in our map, we know that it is a duplicate subtree. We only add the
    // subtree when it hits twice to prevent duplication in our result list.

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
