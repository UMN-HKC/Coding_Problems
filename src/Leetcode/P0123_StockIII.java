package Leetcode;

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
}
