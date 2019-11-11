package Leetcode;

public class P0413_ArithmeticSlices {

    // approach 1: DP
    // dp[i]: the number of arithmetic slices we can find with ith element as the last element of the contiguous subarray
    // so the final result would be the summation of dp[i] ( 3 <= i <= A.length)

    public int numberOfArithmeticSlices(int[] A) {
        int len = A.length;
        int[] dp = new int[len + 1];
        int sum = 0;
        for (int i = 2; i < len; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i + 1] = 1 + dp[i];
            }
            sum += dp[i + 1];
        }
        return sum;
    }

    // optimize space

    public int numberOfArithmeticSlices_2(int[] A) {
        int len = A.length;
        int cur = 0;
        int sum = 0;
        for (int i = 2; i < len; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur += 1;
                sum += cur;
            }
            else {
                cur = 0;
            }
        }
        return sum;
    }
}
