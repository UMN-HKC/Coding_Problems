package Leetcode;

import java.util.Arrays;

public class P0978_LongestTurbulentSubarray {

    // approach 1: DP
    // dp[i]: the maximum length of a turbulent sub array ending at index i
    // The basic idea is that we will use a dp array to keep track of the current maximum
    // turbulent subarray and meanwhile also keep a global max to track the global
    // longest turbulent subarray

    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length == 0) return 0;
        if (A.length == 1) return 1;
        int len = A.length;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < len; i++) {
            if (A[i] == A[i - 1]) {
                continue;
            }
            else if (A[i] > A[i - 1]) {
                if (dp[i - 1] > 0) {
                    dp[i] = 2;
                }
                else {
                    dp[i] = Math.abs(dp[i - 1]) + 1;
                }
            }
            else {
                if (dp[i - 1] < 0) {
                    dp[i] = -2;
                }
                else {
                    dp[i] = -(dp[i - 1] + 1);
                }
            }
            max = Math.max(max, Math.abs(dp[i]));
        }
        return max;
    }

    // optimize space to O(1)
    // incre: the longest turbulent subarray ending at current index with an increasing trend
    // incre: the longest turbulent subarray ending at current index with a decreasing trend

    public int maxTurbulenceSize_2(int[] A) {
        if (A == null || A.length == 0) return 0;
        if (A.length == 1) return 1;
        int len = A.length;
        int max = 1, incre = 1, decre = 1;
        for (int i = 1; i < len; i++) {
            // note that when A[i] == A[i - 1], we
            // need to reset incre and decre to 1
            if (A[i] == A[i - 1]) {
                incre = 1;
                decre = 1;
            }
            else if (A[i] > A[i - 1]) {
                incre = decre + 1;
                decre = 1;
            }
            else {
                decre = incre + 1;
                incre = 1;
            }
            max = Math.max(max, Math.max(incre, decre));
        }
        return max;
    }
}
