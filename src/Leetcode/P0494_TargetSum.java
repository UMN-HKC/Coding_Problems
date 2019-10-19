package Leetcode;


public class P0494_TargetSum {

    // approach 1: DFS
    int res = 0;
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        helper(nums, 0, 0, S);
        return res;
    }
    public void helper(int[] nums, int idx, int sum, int S) {
        if (idx == nums.length) {
            if (sum == S)
                res++;
            return;
        }
        helper(nums, idx + 1, sum + nums[idx], S);
        helper(nums, idx + 1, sum - nums[idx], S);
    }

    // approach 2: DP
    // The basic idea is to realize that the result would always lie within [-sum, sum]
    // The problem is solved using dynammic programming.
    // dp[i][j]: 前i个元素组成j的个数
    // dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i][j] += dp[i - 1][j + nums[i - 1]] for j from 0 to 2 * sum/
    // note that for both dp[i - 1][j - nums[i - 1]] and dp[i - 1][j + nums[i - 1]] we need to check
    // boundary. Also note that columns in the dp array is actually shifted because we cannot
    // represent negative values as our index

    // time: O(sum * n)

    public int findTargetSumWays_2(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S) return 0;
        int len = nums.length;
        int[][] dp = new int[len + 1][2 * sum + 1];
        dp[0][sum] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= 2 * sum; j++) {
                if (j - nums[i - 1] >= 0) dp[i][j] += dp[i - 1][j - nums[i - 1]];
                if (j + nums[i - 1] <= 2 * sum) dp[i][j] += dp[i - 1][j + nums[i - 1]];
            }
        }
        return dp[len][S + sum];
    }

    // approach 3: DP (0/1 Knapsack)
    // dp[i][j]: by using the first i numbers(a subset of first i numbers),
    // the total number of summing to value j

    public int findTargetSumWays_3(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum + S;
        if (sum < S || target % 2 != 0) return 0;
        int len = nums.length;
        int[][] dp = new int[len + 1][target / 2 + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= target / 2; j++) {
                dp[i][j] += dp[i - 1][j];
                if (j - nums[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][target / 2];
    }

    // optimize space for approach 3 and by going backwards, we don't need to copy array
    // since we are not overwriting anything that we will use in the future

    public int findTargetSumWays_4(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum + S;
        if (sum < S || target % 2 != 0) return 0;
        int len = nums.length;
        int[] dp = new int[target / 2 + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = target / 2; j >= nums[i - 1]; j--) {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[target / 2];
    }
}
