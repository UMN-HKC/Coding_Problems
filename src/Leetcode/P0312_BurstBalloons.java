package Leetcode;

public class P0312_BurstBalloons {

    // approach 1: divide and conquer with memo
    // the key point is to realize that we could think about the question not about which balloon burst first
    // but which balloon burst lastly.
    // huahua: https://www.youtube.com/watch?v=z3hu2Be92UA

    // time: O(n^3)
    // space: O(n^2)
    
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] paddedNums = new int[n+2];
        for (int i = 0; i < nums.length; i++) {
            paddedNums[i+1] = nums[i];
        }
        paddedNums[0] = paddedNums[n+1] = 1;
        int[][] dp = new int[n+2][n+2];
        return helper(paddedNums, dp, 0, n+1);
    }
    public int helper(int[] nums, int[][] dp, int start, int end) {
        if (start + 1 == end) return 0;
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        int maxCoins = 0;
        for (int i = start + 1; i < end; i++) {
            maxCoins = Math.max(maxCoins, nums[i]*nums[start]*nums[end] + helper(nums, dp, start, i) + helper(nums, dp, i, end));
        }
        dp[start][end] = maxCoins;
        return maxCoins;
    }
}
