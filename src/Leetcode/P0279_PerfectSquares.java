package Leetcode;

import java.util.Arrays;

public class P0279_PerfectSquares {


    // couldn't figure out initially
    // idea borrowed from discussion board
    // DP solution

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i - j * j >= 0; j++) {
                // dp[i] = Math.min(dp[i - j*j] + dp[j*j], dp[i]) => dp[j * j] is basically 1
                dp[i] = Math.min(dp[i - j * j] + 1, dp[i]);
            }
        }
        return dp[n];
    }
}
