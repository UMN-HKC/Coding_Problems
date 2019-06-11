package Leetcode;

public class P0188_StockIV {

    // idea borrowed from discussion board
    // Basically same as stockIII while with generalized k

    // time: O(k * n)
    // space: O(k * n)

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        //if k >= n/2, then you can make maximum number of transactions.
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i] < prices[i + 1]) {
                    profit += prices[i + 1] - prices[i];
                }
            }
            return profit;
        }
        int n = prices.length;
        int[][] dp = new int[k+1][n];
        for (int num = 1; num < dp.length; num++) {
            int maxDiff = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[num][i] = Math.max(dp[num][i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[num - 1][i] - prices[i]);
            }
        }
        return dp[k][n-1];
    }
}
