package Leetcode;

public class P0918_MaximumSumCircularSubarray {

    // approach 1: DP
    // there are two cases:
    // 1. Maximum sum coming from circular array (combining head subarray and tail subarray)
    // 2. Maximum sum coming from regular array
    // prefixDP[i]: maximum sum from A[0] to A[i] where 0 <= i < n - 1 and must be contiguous starting from left
    // suffixDP[i]: maximum sum from A[n - 1] to A[i] where 0 <= 0 < n and must be contiguous starting from right
    // Above two dp array are used for case 1, and for case 2, we can calculate it separatelt
    // and finally, we would return the greater value between these two cases.

    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int[] prefixDP = new int[n + 1];
        int[] suffixDP = new int[n + 1];

        int curSum = 0;
        // prefix from taking 0 to n - 1 elements
        for (int i = 0; i < n - 1; i++) {
            curSum += A[i];
            prefixDP[i + 1] = Math.max(prefixDP[i], curSum);
        }
        // suffix from taking 1 to n elements
        curSum = A[n - 1];
        suffixDP[n] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            curSum += A[i];
            suffixDP[i + 1] = Math.max(suffixDP[i + 2], curSum);
        }
        int res = prefixDP[0] + suffixDP[1];
        for (int i = 0; i < n - 1; i++) {
            res = Math.max(res, prefixDP[i + 1] + suffixDP[i + 2]);
        }
        int globalMax = A[0];
        int maxEndingHere = A[0];
        for (int i = 1; i < n; i++) {
            maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
            globalMax = Math.max(globalMax, maxEndingHere);
        }
        return Math.max(res, globalMax);

    }

    // approach 2: one pass
    // https://leetcode.com/problems/maximum-sum-circular-subarray/discuss/178422/One-Pass
    // max(the max subarray sum, the total sum - the min subarray sum)
    
    public int maxSubarraySumCircular(int[] A) {
        int n = A.length;
        int total = A[0], maxEndingHere = A[0], minEndingHere = A[0], globalMax = A[0], globalMin = A[0];
        for (int i = 1; i < n; i++) {
            maxEndingHere = Math.max(A[i] + maxEndingHere, A[i]);
            globalMax = Math.max(globalMax, maxEndingHere);
            minEndingHere = Math.min(minEndingHere + A[i], A[i]);
            globalMin = Math.min(globalMin, minEndingHere);
            total += A[i];
        }

        if (total == globalMin) return globalMax;
        else {
            return globalMax > (total - globalMin) ? globalMax : total - globalMin;
        }
    }
}
