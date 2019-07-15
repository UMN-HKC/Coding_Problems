package Leetcode;

public class P0152_MaximumProductSubarray {

    // approach 1: dp

    // time: O(n)
    // space: O(n)
    // could be optimized to O(1) for space complexity

    // The basic idea is to realize the problem could be solved using DP, and also
    // note since there is negative numbers(the product of two negative numbers is positive)
    // we need to record both max and min of accumulated product so far.
    // transition function:
    // max[i] = max(nums[i], max(max[i-1] * nums[i], min[i-1] * nums[i]))
    // min[i] = min(nums[i], min(max[i-1] * nums[i], min[i-1] * nums[i]))

    public int maxProduct_1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] max = new int[nums.length + 1];
        int[] min = new int[nums.length + 1];
        int res = Integer.MIN_VALUE;
        max[0] = min[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            max[i+1] = Math.max(Math.max(min[i] * nums[i], max[i] * nums[i]), nums[i]);
            min[i+1] = Math.min(Math.min(min[i] * nums[i], max[i] * nums[i]), nums[i]);
            res = Math.max(res, max[i+1]);
        }
        return res;
    }

    // optimize space

    // time: O(n)
    // space: O(1)

    public int maxProduct_2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max, min, res;
        max = min = res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int temp = max;
            max = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i] * temp, nums[i] * min));
            res = Math.max(max, res);
        }
        return res;
    }
}
