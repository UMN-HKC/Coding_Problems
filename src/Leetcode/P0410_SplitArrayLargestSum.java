package Leetcode;

public class P0410_SplitArrayLargestSum {

    // approach 1: binary search

    // binary search on [max, sum], for each mid value, calculate how many
    // pieces we need and compare that with m

    public int splitArray(int[] nums, int m) {
        int sum = 0;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        if (m == nums.length) return max;
        if (m == 1) return sum;
        int l = max, r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (moreThanMCuts(nums, mid, m)) {
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }
    public boolean moreThanMCuts(int[] nums, int target, int limit) {
        int cuts = 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > target) {
                cuts++;
                sum = num;
            }
            if (cuts > limit) {
                return true;
            }
        }
        return false;
    }

    // approach 2: DP
    // dp[i][j]: the minimum largest sum from spliting nums[0:j] to i parts

    public int splitArray_2(int[] nums, int m) {
        int len = nums.length;
        long[] prefixSum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        // dp[i][j]: the minimum largest sum from spliting nums[0:j] to i parts
        long[][] dp = new long[m + 1][len + 1];
        for (int i = 1; i < dp[0].length; i++) {
            dp[1][i] = prefixSum[i];
        }
        for (int i = 2; i < dp.length; i++) {
            for (int j = i; j <= len; j++) {
                dp[i][j] = Long.MAX_VALUE;
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], prefixSum[j] - prefixSum[k]));
                }
            }
        }
        return (int)dp[m][len];
    }
}
