package Leetcode;

public class P1035_UncrossedLines {

    // approach 1: DP
    // dp[i][j] represents max uncrossed lines we can get using A[0:i-1] and B[0:j-1]

    public int maxUncrossedLines(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j++) {
                if (A[i] == B[j]) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                }
                else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[lenA][lenB];
    }
}
