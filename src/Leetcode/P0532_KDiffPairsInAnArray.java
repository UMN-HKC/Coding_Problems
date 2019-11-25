package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0532_KDiffPairsInAnArray {

    // approach 1: hashmap
    // The basic idea is to use hashmap to store the frequency of each number
    // and then traverse each entry. For each entry, we regard its key as the potential
    // greater number from which we subtract the smaller number to get k
    // There are 2 cases here:
        // - when k == 0, we check if the value of the entry is greater or equal to 2
        // - when k != 0, we check if map contains the smaller number
    // since we only traverse entry, each bigger number we visit we only visit it once.

    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) map.put(num, map.getOrDefault(num, 0) + 1);
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() >= 2) res++;
            }
            else {
                if (map.containsKey(entry.getKey() - k)) res++;
            }
        }
        return res;
    }
}
