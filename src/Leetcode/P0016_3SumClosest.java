package Leetcode;

import java.util.Arrays;

public class P0016_3SumClosest {

    // approach 1: two pointers

    // time: O(n)

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = nums[0] + nums[1] + nums[nums.length - 1];
        int dif = sum - target;
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int temp = nums[l] + nums[r] + nums[i];
                if (temp == target) {
                    return temp;
                }
                else if (temp < target) {
                    l++;
                }
                else {
                    r--;
                }
                // note that we compare its difference in absolute value
                if (Math.abs(temp - target) < Math.abs(sum - target)) {
                    sum = temp;
                }
            }
        }
        return sum;
    }
}
