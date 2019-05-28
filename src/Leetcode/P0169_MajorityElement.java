package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0169_MajorityElement {
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
