package Leetcode;

public class P1043_PartitionArrayForMaximumSum {

    // approach 1: DP
    // The basic idea is to realize it as a dynamic programming problem because how we
    // solve the sub problem and thus different values of sub problems will have an effect
    // on the result of its bigger problem.
    // dp[i]: the maximum sum we can get from the first i elements when the maximum partitioning
    // size is k. To calculate dp[i], we will need to look back on dp[i - 2], dp[i - 3],...,dp[i - k]
    // which means that we are trying to include ith element with its previous elements. So the result
    // for dp[i] would be the sum of all included elements, say we included j elements with i
    // as its end element, result would be j * max(A[i],A[i-1],...,A[i - j]) plus dp[i - j + 1]
    // which is previous sub problem we solved.

    public int maxSumAfterPartitioning(int[] A, int K) {
        int len = A.length;
        int[] dp = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            int curMax = A[i - 1];
            dp[i] = curMax + dp[i - 1];
            for (int j = 1; j < K && i - j > 0; j++) {
                curMax = Math.max(curMax, A[i - 1 - j]);
                dp[i] = Math.max(dp[i], dp[i - j - 1] + curMax * (j + 1));
            }
        }
        return dp[len];
    }
}
