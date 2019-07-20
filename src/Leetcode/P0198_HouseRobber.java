package Leetcode;

public class P0198_HouseRobber {

    // approach 1: DP
    // either rob it or not rob it

    // time: O(n)
    // space: O(n)

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            if (i == 1) {
                dp[1] = nums[0];
            }
            else {
                dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
            }
        }
        return dp[nums.length];
    }

    // optimize space to O(1)

    public int rob_optimize(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int robLast = nums[0], notRobLast = 0;
        for (int i = 1; i < nums.length; i++) {
            int temp = robLast;
            robLast = Math.max(robLast, notRobLast + nums[i]);
            notRobLast = temp;
        }
        return Math.max(robLast, notRobLast);
    }

}
