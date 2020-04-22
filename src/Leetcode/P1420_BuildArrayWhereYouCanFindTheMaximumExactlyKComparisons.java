package Leetcode;

public class P1420_BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons {

    // approach 1: dp

    public static int modulo = (int)Math.pow(10, 9) + 7;
    public int numOfArrays(int n, int m, int k) {
        int[][][] dp = new int[n + 1][m + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int l = 0; l <= k; l++) {
                    dp[i][j][l] = -1;
                }
            }
        }
        return dfs(n, m, k, 0, 0, 0, dp);
    }
    private int dfs(int n, int m, int k, int curIdx, int curMax, int kUsed, int[][][] dp) {
        if (curIdx == n) {
            if (kUsed == k) {
                return 1;
            }
            return 0;
        }
        if (kUsed > k) return 0;
        if (dp[curIdx][curMax][kUsed] != -1) return dp[curIdx][curMax][kUsed];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            int nextMax = curMax >= i ? curMax : i;
            int nextKUsed = curMax >= i ? kUsed : kUsed + 1;
            res += dfs(n, m, k, curIdx + 1, nextMax, nextKUsed, dp);
            res %= modulo;
        }
        return dp[curIdx][curMax][kUsed] = res;
    }
}
