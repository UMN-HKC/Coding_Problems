package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0904_FruitIntoBaskets {

    // approach 1: sliding window
    // Despite the poor description of the problem, it is basically finding the longest
    // sub array with at most 2 distinct elements

    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        int maxLength = 1;
        while (j < tree.length) {
            map.put(tree[j], map.getOrDefault(tree[j], 0) + 1);
            j++;
            if (map.size() == 3) {
                while (map.size() == 3) {
                    map.put(tree[i], map.get(tree[i]) - 1);
                    if (map.get(tree[i]) == 0) map.remove(tree[i]);
                    i++;
                }
            }
            maxLength = Math.max(j - i, maxLength);
        }
        return maxLength;
    }
}
