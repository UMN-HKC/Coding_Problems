package Leetcode;

public class P0256_PaintHouse {

    // brute force
    public int minCost(int[][] costs) {
        return Math.min(Math.min(dfs(costs, 0, 0, 0), dfs(costs, 0, 0, 1)), dfs(costs, 0, 0, 2));
    }
    public int dfs(int[][] costs, int index, int cost, int color) {
        if (index == costs.length) {
            return cost;
        }
        else {
            if (color == 0) {
                return Math.min(dfs(costs, index + 1, cost + costs[index][0], 1),dfs(costs, index + 1, cost + costs[index][0], 2));
            }
            else if (color == 1) {
                return Math.min(dfs(costs, index + 1, cost + costs[index][1], 0),dfs(costs, index + 1, cost + costs[index][1], 2));
            }
            else {
                return Math.min(dfs(costs, index + 1, cost + costs[index][2], 0),dfs(costs, index + 1, cost + costs[index][2], 1));
            }
        }
    }

    // approach 2: DP

    public int minCost_dp(int[][] costs) {
        int n = costs.length;
        int c = 3;
        int[][] dp = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i-1][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i-1][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i-1][2];
        }
        return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
    }

    // DP with space optimized to O(1)
    public int minCost_dp_2(int[][] costs) {
        int n = costs.length;
        int r = 0, g = 0, b = 0;
        for (int i = 0; i < n; i++) {
            int rr = r, gg = g, bb = b;
            r = costs[i][0] + Math.min(gg, bb);
            g = costs[i][1] + Math.min(rr, bb);
            b = costs[i][2] + Math.min(rr, gg);
        }
        return Math.min(r, Math.min(g, b));
    }
}
