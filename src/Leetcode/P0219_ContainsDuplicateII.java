package Leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P0219_ContainsDuplicateII {


    // initial approach: use a map to store the nearest index of a number that we scanned previously
    // and once we encounter a previously met number and its index is within k distance, return true

    // time: O(n)
    // space: O(n)

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && (i - map.get(nums[i]) <= k)) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    // approach 2: sliding window thinking
    // only keep k-size 'window' of elements

    // time: O(n)
    // space: O(k)

    public boolean containsNearbyDuplicate_sliding_window(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(i > k) set.remove(nums[i-k-1]);
            if(!set.add(nums[i])) return true;
        }
        return false;
    }
}
