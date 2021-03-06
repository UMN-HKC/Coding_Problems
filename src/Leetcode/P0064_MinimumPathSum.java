package Leetcode;

public class P0064_MinimumPathSum {

    // approach 1: DP
    // the basic idea is to realize dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1]
    // and also when on the left and top edge, path sum only comes from either its left or top plus its own

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int r = grid.length, c = grid[0].length;
        int[][] dp = new int[r+1][c+1];
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if (i == 1) dp[i][j] = dp[i][j-1] + grid[i-1][j-1];
                else if (j == 1) dp[i][j] = dp[i-1][j] + grid[i-1][j-1];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i-1][j-1];
            }
        }
        return dp[r][c];
    }

    // optimize space

    public int minPathSum_2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] pre = new int[n + 1];
        int[] cur = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1) cur[j] = cur[j - 1] + grid[i - 1][j - 1];
                else if (j == 1) cur[j] = pre[j] + grid[i - 1][j - 1];
                else cur[j] = Math.min(pre[j], cur[j - 1]) + grid[i - 1][j - 1];
            }
            int[] temp = cur;
            cur = pre;
            pre = temp;
        }
        return pre[n];
    }
}
