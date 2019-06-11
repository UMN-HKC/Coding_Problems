package Leetcode;

import java.util.Arrays;

public class P0123_StockIII {

    // couldn't figure out initially
    // idea borrowed from discussion board

    // approach 1: DP
    // We can generalize two transactions to k transactions, but in here, k = 2
    // The basic idea is that for k transactions, on an ith day the maximum prifit would be
    // the maximum between:
    //  - do not make a transaction (dp[k][i-1]) so the profit stays same with (i-1)th day
    //  - make a transaction (determine the best buy date by looping from 0th day to (i - 1)th
    //    day in terms of dp[k - 1])

    // time: O(k * (n^2))
    // space: O(k * n)

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int k = 2;
        int n = prices.length;
        int[][] dp = new int[k+1][n];
        for (int num = 1; num < dp.length; num++) {
            for (int i = 1; i < n; i++) {
                int tempMax = 0;
                for (int j = 0; j < i; j++) {
                    tempMax = Math.max(tempMax, dp[num - 1][j] + prices[i] - prices[j]);
                }
                dp[num][i] = Math.max(dp[num][i - 1], tempMax);
            }
        }
        return dp[k][n-1];
    }

    // optimize time from O(k * (n^2)) to O(k * n)
    // The basic idea is that in the previous implementation, we are able to observe that in the
    // innermost loop, the only changing value is (dp[num - 1][j] - prices[j]), and this is where
    // we can optimize the time complexity by updating this value each time

    // time: O(k * n))
    // space: O(k * n)

    public int maxProfit_optimize_time(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int k = 2;
        int n = prices.length;
        int[][] dp = new int[k+1][n];
        for (int num = 1; num < dp.length; num++) {
            // maxDiff is initially -prices[0](dp[num - 1][0] - prices[0] == -prices[0])
            int maxDiff = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[num][i] = Math.max(dp[num][i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[num - 1][i] - prices[i]);
            }
        }
        return dp[k][n-1];
    }

    // optimize space: still not quite get how it works
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/discuss/135704/Detail-explanation-of-DP-solution

    public int maxProfit_optimize_space(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int k = 2;
        int n = prices.length;
        int[] dp = new int[k+1];
        // since now we iterate through number of transactions insides
        // each iteration of days, we need to store multiple in-progress
        // value of maxDiff for different number of transactions
        int[] maxDiff = new int[k+1];
        Arrays.fill(maxDiff, -prices[0]);

        for (int i = 1; i < prices.length; i++) {
            for (int num = 1; num <= k; num++) {
                maxDiff[num] = Math.max(maxDiff[num], dp[num - 1] - prices[i]);
                dp[num] = Math.max(dp[num], maxDiff[num] + prices[i]);
            }
        }
        return dp[k];
    }

}
