package Leetcode;

import java.util.Arrays;

public class P0377_CombinationSumIV {

    // initial approach dfs which will cause TLE because we are basically doing a lot of
    // duplicate calculations. So, it leads us to think about store the intermediate computation
    // result with another approach which is dynamic programming.

    // time: exponential
    // space: O(target) stack space

    public int combinationSum4_initial(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) return 0;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += combinationSum4_initial(nums, target - nums[i]);
        }
        return sum;
    }

    // approach 2: DP (top down)

    // time: O(target * n) but in practice, top-down might be faster than  bottom-up
    // because top-down only calculates necessary intermediate steps to reach our result
    // while bottom-up dp approach calculates all intermediate steps
    
    // space: O(target)

    public int combinationSum4_dp_topdpwn(int[] nums, int target) {
        int[] helper = new int[target + 1];
        Arrays.fill(helper, -1);
        helper[0] = 1;
        return dfs(nums, helper, target);
    }
    public int dfs(int[] nums, int[] helper, int target) {
        if (target < 0) {
            return 0;
        }
        if (helper[target] != -1) {
            return helper[target];
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += dfs(nums, helper, target - nums[i]);
        }
        helper[target] = sum;
        return sum;
    }

    // approach 3: DP (bottom up)

    // time: O(target * n)
    // space: O(target)

    public int combinationSum4_dp_bottom_up(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
