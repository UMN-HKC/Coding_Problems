package Leetcode;
import java.util.*;


public class P0265_PaintHouseII {

    // initial approach: DP brute force

    // time: O(n * (k^2))
    // space: O(n * k)

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n + 1][k];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                int curCost = costs[i - 1][j];
                int prevCost = Integer.MAX_VALUE;
                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        prevCost = Math.min(prevCost, dp[i - 1][l]);
                    }
                }
                dp[i][j] = Math.min(curCost + (prevCost == Integer.MAX_VALUE ? 0 : prevCost), dp[i][j]);

            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            res = Math.min(res, dp[n][i]);
        }
        return res;
    }

    // approach 2: dp approach with time O(n * k) and space O(1)
    // yavinci: https://leetcode.com/problems/paint-house-ii/discuss/69509/Easiest-O(1)-space-JAVA-solution

    public int minCostII_dp_optimized(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int prevMin1 = 0, prevMin2 = 0, minIdx = -1;

        for (int i = 0; i < costs.length; i++) {
            int curMin1 = Integer.MAX_VALUE, curMin2 = Integer.MAX_VALUE, curMinIdx = -1;
            for (int j = 0; j < costs[0].length; j++) {
                int cost = costs[i][j] + (minIdx == j ? prevMin2 : prevMin1);
                if (cost < curMin1) {
                    curMin2 = curMin1;
                    curMin1 = cost;
                    curMinIdx = j;
                }
                else if (cost < curMin2) {
                    curMin2 = cost;
                }
            }
            prevMin1 = curMin1;
            prevMin2 = curMin2;
            minIdx = curMinIdx;
        }
        return prevMin1;
    }
}
