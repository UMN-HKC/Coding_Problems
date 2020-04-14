package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0525_ContiguousArray {

    // approach 1: hashmap
    // https://leetcode.com/problems/contiguous-array/discuss/99655/Python-O(n)-Solution-with-Visual-Explanation

    // The idea is to use a hashmap and a counter. Each time we encounter 0
    // decrement counter by 1 and encounter 1 increment counter by 1.
    // Store unique counter value(first time encountered) and index to map,
    // once we find a previously encountered counter value, we know that
    // a sequence of equal number of 1's and 0's have appeared

    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                cnt--;
            }
            else {
                cnt++;
            }
            if (map.containsKey(cnt)) {
                max = Math.max(max, i - map.get(cnt));
            }
            else {
                map.put(cnt, i);
            }
        }
        return max;
    }
}
