package Leetcode;
import java.util.*;


public class P0325_MaximumSizeSubarraySumEqualsK {

    // approach 1: hashmap + running sum
    // the basic idea is to use hashmap to store the smallest index where
    // a particular sum occurs(we only add a sum to map if it is its first time)
    // use a runningSum and a max to update our answer

    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int runningSum = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            if (map.containsKey(runningSum - k)) {
                max = Math.max(max, i - map.get(runningSum - k));
            }
            if (!map.containsKey(runningSum)) {
                map.put(runningSum, i);
            }
        }
        return max;
    }
}
