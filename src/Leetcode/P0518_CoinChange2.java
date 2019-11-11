package Leetcode;

public class P0518_CoinChange2 {

    // approach 1: DP (knapsack)
    // dp[i][j]: number of ways to form j amount using the first ith coins
    // note that in this problem, one coin can be used multiple times, so
    // in some other knapsack problem, one item could only be used once which
    // may result in dp[i - 1][j - coin] but here we access dp[i][j - coin]

    public int change(int amount, int[] coins) {
        int numOfCoins = coins.length;
        int[][] dp = new int[numOfCoins + 1][amount + 1];
        for (int i = 0; i <= numOfCoins; i++) dp[i][0] = 1;
        for (int i = 1; i <= numOfCoins; i++) {
            int coin = coins[i - 1];
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];  // not using current one
                // using current one, note that we still use i instead of i - 1
                // because one coin can be used multiple times
                dp[i][j] += j - coin >= 0 ? dp[i][j - coin] : 0;
            }
        }
        return dp[numOfCoins][amount];
    }
}
