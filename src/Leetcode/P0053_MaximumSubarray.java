package Leetcode;

public class P0053_MaximumSubarray {

    // approach 1: prefix sum
    // the basic idea is to use an auxiliary array to store the running sum
    // and then iterate through the running sum array while keep a previous
    // minimum sum so that we can calculate current maximum sum

    public int maxSubArray_prefixSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] runningSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            runningSum[i + 1] = runningSum[i] + nums[i];
        }
        int max = Integer.MIN_VALUE;
        int preMin = 0;
        for (int i = 1; i < runningSum.length; i++) {
            max = Math.max(runningSum[i] - preMin, max);
            preMin = Math.min(preMin, runningSum[i]);
        }
        return max;
    }

    // approach 2: dp
    // the basic idea is to know that the maximum sum for current position is
    // either (previous continuous maximum sum + current) or (current), and use
    // a max variable to keep track of global maximum

    // transition function: dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);

    public int maxSubArray_dp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i - 1], nums[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // approach 3:

    public int maxSubArray(int[] nums) {

        int max = nums[0];
        int accum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, Math.max(accum + nums[i], nums[i]));
            accum = Math.max(nums[i], accum + nums[i]);
        }
        return max;
    }
}
