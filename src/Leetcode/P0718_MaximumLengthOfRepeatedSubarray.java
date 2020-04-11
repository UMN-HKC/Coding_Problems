package Leetcode;

public class P0718_MaximumLengthOfRepeatedSubarray {

    // approach 1: dp
    // dp[i][j]: the maximum length of repeated subarray matching A[0:i] with B[0:j]
    // and with the last matching number being B[j]

    // time: O(n^2)
    // space: O(n^2) -> can be optimized to O(n)

    public int findLength(int[] A, int[] B) {
        int n = A.length;
        if (n == 0) return 0;
        int[][] dp = new int[n + 1][n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (B[i] == A[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    max = Math.max(max, dp[i + 1][j + 1]);

                }
            }
        }
        return max;
    }
}
