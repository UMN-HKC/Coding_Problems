package Leetcode;

import java.util.Arrays;

public class P1498_NumberOfSubsequencesThatSatisfyTheGivenSumCondition {

    // approach 1: two pointers + math
    // https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/discuss/709227/JavaC%2B%2BPython-Two-Sum

    public static int modulo = (int)Math.pow(10, 9) + 7;
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] pows = new int[n];
        pows[0] = 1;
        for (int i = 1; i < n; i++) {
            pows[i] = pows[i - 1] * 2 % modulo;
        }
        int l = 0, r = nums.length - 1;
        int res = 0;

        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            }
            else {
                res = (res + pows[r - l]) % modulo;
                l++;
            }
        }
        return res;
    }
}
