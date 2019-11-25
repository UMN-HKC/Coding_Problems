package Leetcode;

public class    P0727_MinimumWindowSubsequence {

    // approach 1: DP

    // dp[i][j] represents the minimum subsequence ending at s[j] that matches t[0:i] .
    // Note that dp[i][j] == 0 means no matching so far. So only positive integers
    // means we have matched t[0:i]

    public String minWindow(String S, String T) {
        if (S == null || S.length() == 0) return "";
        int sLen = S.length();
        int tLen = T.length();
        int[][] dp = new int[tLen + 1][sLen + 1];
        for (int i = 1; i <= tLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                if (S.charAt(j - 1) != T.charAt(i - 1)) {
                    dp[i][j] = dp[i][j - 1] ==  0 ? 0 : dp[i][j - 1] + 1;
                }
                else {
                    dp[i][j] = (i != 1 && dp[i - 1][j - 1] == 0) ? 0 : dp[i - 1][j - 1] + 1;
                }
            }
            if (i == tLen) {
                String res = "";
                int minLen = Integer.MAX_VALUE;
                for (int k = sLen; k >= tLen; k--) {
                    if (dp[i][k] != 0 && S.charAt(k - 1) == T.charAt(tLen - 1) && dp[i][k] <= minLen) {
                        res = S.substring(k - dp[i][k], k);
                        minLen = dp[i][k];
                    }
                }
                return res;
            }
        }
        return "";
    }
}
