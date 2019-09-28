package Leetcode;

public class P0188_StockIV {

    // The idea is to use bottom-up DP. Basically same as stockIII while with generalized k
    // The following evolution of implementation is best for me to understand

    // okay, so let's first see the approach without optimization

    // initial approach: (MLE)
    // time: O(k * (n^2))
    // space: O(k * n)

    public int maxProfit_1(int k, int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int n = prices.length;
        int[][] dp = new int[k + 1][n];
        for (int t = 1; t <= k; t++) {
            for (int i = 1; i < n; i++) {
                int temp = 0;
                for (int j = 0; j < i; j++) {
                    temp = Math.max(temp, dp[t - 1][j] - prices[j] + prices[i]);
                }
                dp[t][i] = Math.max(dp[t][i - 1], temp);
            }
        }
        return dp[k][n - 1];
    }

    // Now let's do some optimization for the space (TLE)
    // time: O(k * (n^2))
    // space: O(n)

    public int maxProfit_2(int k, int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int n = prices.length;
        int[] transaction1 = new int[n];
        int[] transaction2 = new int[n];
        for (int t = 0; t < k; t++) {
            for (int i = 1; i < n; i++) {
                int temp = 0;
                for (int j = 0; j < i; j++) {
                    temp = Math.max(temp, transaction1[j] - prices[j] + prices[i]);
                }
                transaction2[i] = Math.max(transaction2[i - 1], temp);
            }
            int[] transaction = transaction1;
            transaction1 = transaction2;
            transaction2 = transaction;
        }
        return transaction1[n - 1];
    }

    // Okay now TLE. We can notice that we can incorporate the innermost loop with the outer loop
    // this is the most optimized approach under my belt right now

    // time: O(k * n)
    // space: O(k * n)

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        if (k >= prices.length / 2) {
            return greedySolve(prices);
        }
        int n = prices.length;
        int[] transaction1 = new int[n];
        int[] transaction2 = new int[n];
        for (int t = 0; t < k; t++) {
            int maxDiff = -prices[0];
            for (int i = 1; i < n; i++) {
                transaction2[i] = Math.max(transaction2[i - 1], prices[i] + maxDiff);
                maxDiff = Math.max(maxDiff, transaction1[i] - prices[i]);
            }
            int[] transaction = transaction1;
            transaction1 = transaction2;
            transaction2 = transaction;
        }
        return transaction1[n - 1];
    }
    public int greedySolve(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }
}
