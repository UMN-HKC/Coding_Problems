package Leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class P1130_MinimumCostTreeFromLeafValues {

    // approach 1: DP

    // dp[i][j]: the smallest possible sum of the values for arr[i] to arr[j]
    // The basic idea is to build up the dp array from smaller scale to larger scale.
    // How to do that? First, start with the length of 2 subarray and build up tp length of n
    // array. For each length window, we move the window from the left towards right.
    // For each window, iterate through each slot inside window, dp[i][i + len - 1] will be
    // updated by each slot split (j):
    // dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], dp[i][j] + dp[j + 1][i + len - 1]
    //                            + max[i][j] * max[j + 1][i + len - 1]);

    // smaller length(sub problem)
    // time: O(n^3)
    // space: O(n^2)

    public int mctFromLeafValues(int[] arr) {
        int m = arr.length;
        int[][] max = new int[m][m];
        int[][] dp = new int[m][m];
        for (int i = 0; i < m; i++) {
            int localMax = arr[i];
            for (int j = i; j < m; j++) {
                localMax = Math.max(localMax, arr[j]);
                max[i][j] = localMax;
            }
        }
        for (int len = 2; len <= m; len++) {
            for (int i = 0; i + len <= m; i++) {
                dp[i][i + len - 1] = Integer.MAX_VALUE;
                for (int j = i; j < i + len - 1; j++) {
                    dp[i][i + len - 1] = Math.min(dp[i][i + len - 1], dp[i][j] + dp[j + 1][i + len - 1]
                            + max[i][j] * max[j + 1][i + len - 1]);
                }
            }
        }
        return dp[0][m - 1];
    }

    // approach 2: Stack
    // https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/discuss/339959/One-Pass-O(N)-Time-and-Space

    public int mctFromLeafValues_2(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        stack.push(Integer.MAX_VALUE);
        for (int num : arr) {
            while (!stack.isEmpty() && stack.peek() <= num) {
                res += stack.pop() * (Math.min(stack.peek(), num));
            }
            stack.push(num);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }
}
