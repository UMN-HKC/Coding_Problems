package Leetcode;

public class P1458_MaxDotProductOfTwoSubsequences {

    // approach 1: DP
    // dp[i][j] represents the maximum dot product we could get for nums1[0:i] and nums2[0:j]
    // there are two cases:
    // - when either i or j is 0, in this case, we must take these two number to form a dot product (there's at least one pair)
    // - otherwise, we could either take the dot product from nums1[i] and nums2[j] and add to dp[i - 1][j - 1]; or we could
    //   choose not to add this dot product and get the greater one from dp[i - 1][j] and dp[i][j - 1]. Note that in this second
    //   case, we need to be careful of negative value from dp[i - 1][j - 1] if we decide to take the dot product of nums1[i] and nums2[j].
    //   If dp[i - 1][j - 1] is negative, we only take the current dot product and leave out dp[i - 1][j - 1].
    

    public int maxDotProduct(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = Integer.MIN_VALUE;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i + 1][j + 1] = Math.max(Math.max(dp[i + 1][j], dp[i][j + 1]), nums1[i] * nums2[j]);
                }
                else {
                    dp[i + 1][j + 1] = nums1[i] * nums2[j] + Math.max(dp[i][j], 0);
                    dp[i + 1][j + 1] = Math.max(Math.max(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
