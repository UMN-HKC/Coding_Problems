package Leetcode;

public class P1143_LongestCommonSubsequence {

    // approach 1: bottom-up dp

    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                }
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], Math.max(dp[i][j + 1], dp[i + 1][j]));
            }
        }
        return dp[len1][len2];
    }

    // approach 2: space optimized

    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[] prev = new int[len2 + 1];
        int[] cur = new int[len2 + 1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    cur[j + 1] = 1 + prev[j];
                }
                cur[j + 1] = Math.max(cur[j + 1], Math.max(prev[j + 1], cur[j]));
            }
            int[] temp = cur;
            cur = prev;
            prev = temp;
        }
        return prev[len2];
    }
}
