package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0666_PathSumIV {

    // approach 1: hashmap + simulate tree
    // For a regular tree data strucutre, we would traverse the tree and once we reach leaf
    // node, we will update(sum to) our result of the path sum. In this problem, we can simulate
    // the tree structure with hashmap where we combine depth and position as key. To get to the
    // left and right children, a simple math will do.

    public int pathSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int key = num / 10;
            int val = num % 10;
            map.put(key, val);
        }
        return traverse(map, 11, 0);
    }
    public int traverse(Map<Integer, Integer> map, int key, int preSum) {
        if (!map.containsKey(key)) return 0;

        int curSum = preSum + map.get(key);
        int left = (key / 10 + 1) * 10 + (key % 10 * 2 - 1);
        int right = (key / 10 + 1) * 10 + key % 10 * 2;
        if (!map.containsKey(left) && !map.containsKey(right)) return curSum;

        return traverse(map, left, curSum) + traverse(map, right, curSum);
    }
}
