package Leetcode;
import java.util.*;

public class P0560_SubarraySumEqualsK {

    // approach 1: hashmap

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] runningSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            runningSum[i+1] = runningSum[i] + nums[i];
        }
        int cnt = 0;
        // initialize map with an initial count eg: [2], k=2
        map.put(0, 1);
        for (int i = 1; i <= nums.length; i++) {
            // get the frequency count of previous subarray sum that could be subtracted from
            // current runningsum and resulting in k
            if (map.containsKey(runningSum[i] - k)) {
                cnt += map.get(runningSum[i] - k);
            }
            // put how many current runningsum we have and update it
            map.put(runningSum[i], map.getOrDefault(runningSum[i], 0) + 1);

        }
        return cnt;
    }
}
