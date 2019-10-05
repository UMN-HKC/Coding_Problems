package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0169_MajorityElement {

    // approach 1: hashmap

    // time: O(n)
    // space: O(n)

    public int majorityElement_initial(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int key : map.keySet()) {
            if (map.get(key) > (n / 2)) {
                return key;
            }
        }
        return 1;
    }

    // approach 2: Boyer-Moore voting

    // The basic idea is that we start with a random candidate number and a counter set to 0
    // During the first iteration, if the current number is the candidate number, we increment
    // the counter, if not we decrement the counter. Once the counter reaches 0, the next number
    // we encounter would be the next candidate.
    // For the second iteration, we will check if the candidate is the real majority number.

    // time: O(n)
    // space: O(1)


    public int majorityElement_moore(int[] nums) {
        int majority = nums[0];
        int cnt = 0;
        for (int num : nums) {
            if (cnt == 0) {
                cnt++;
                majority = num;
            }
            else if (num == majority) {
                cnt++;
            }
            else {
                cnt--;
            }
        }
        return majority;
    }
}
