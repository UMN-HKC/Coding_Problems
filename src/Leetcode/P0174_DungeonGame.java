package Leetcode;

public class P0174_DungeonGame {

    // couldn't figure out initially, idea borrowed from discussion board

    // the basic idea is to realize it is a DP problem, and figure out
    // the transition function and its meaning dp[x][y] represents the
    // minimum hp required to reach bottom right (p). So, it is easier
    // and makes more sense to start from bottom right and work our way
    // to the knight position, since if we start from knight, we cannot
    // know in advance the minimum hp required.

    // the transition function is not simply finding a max or min

    // dp[x][y] = max(1, min(dp[x+1][y], dp[x][y+1]) - hp[x][y]) where
    // the inner min represents the minimum hp required to reach the princess
    // from previous step and the outer max represents the after healing or
    // losing hp in current step, if required hp is negative (we have more hp than
    // we need), we pick 1 instead since we at least need 1 hp to enter next room;
    // if required hp is positive (we at least need this much hp, we pick the greater
    // one between 1 and required hp)

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0) return 0;
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= n; i++) {
            dp[m][i] = Integer.MAX_VALUE;
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}
