package Leetcode;

public class P0375_GuessNumberHigherOrLower {

    // couldn't figure out, idea borrowed from discussion board
    // The main idea is to use minimax thinking, which means always assume you have bad luck and each guess you make
    // will lead you to worse situations, but you cannot give in so you need to make the optimized choice
    // among those worst situations. The problem suggests use dynammic programming.
    //
    // The transition function is: f(i, j) = Min(loss, x + Max(dp[i][x-1], dp[x+1][j])
    // where: (loss = x + Max(dp[i][x-1], dp[x+1][j]) and (i <= x < j)
    // for numbers between i and j, each x we choose, we will have a loss (loss = x + Max(dp[i][x-1], dp[x+1][j]))
    // Why do we choose a greater loss? Because this is due to the bad luck and we want to have enough money
    // prepared in bag so that we guarantee to win the game eventually. Why do we then choose a minimum?
    // Because we want to minimize the maximum loss.

    // approach 1: top-down recursive DP

    public int getMoneyAmount_top_down(int n) {
        int[][] DP = new int[n+1][n+1];
        return DP(DP, 1, n);
    }
    public int DP(int[][] DP, int start, int end) {
        if (DP[start][end] != 0) {
            return DP[start][end];
        }
        if (start >= end) return 0;
        int localMin = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            localMin = Math.min(localMin, i + Math.max(DP(DP, start, i - 1), DP(DP, i + 1, end)));
        }
        DP[start][end] = localMin;
        return DP[start][end];
    }

    // approach 2: bottom-up DP

    public int getMoneyAmount_bottom_up(int n) {
        int[][] DP = new int[n+1][n+1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i >= 1 ; i--) {
                int localMin = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    localMin = Math.min(localMin, Math.max(k + DP[i][k - 1], k + DP[k + 1][j]));
                }
                // need to check i == j - 1 because when i == j - 1, inner loop won't be executed
                // so that localMin is still MAX_VALUE and we don't want that
                DP[i][j] = i == j - 1 ? i : localMin;
            }

        }
        return DP[1][n];
    }
}
