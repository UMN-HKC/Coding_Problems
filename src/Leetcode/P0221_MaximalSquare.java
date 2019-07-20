package Leetcode;

public class P0221_MaximalSquare {


    // approach 1: DP
    // dp[i][j] means the maximum area of square we can get at this position
    // by expanding to its left and up

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    // two min function guarantees the square
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    // one max function updates the maximum area
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }
}
