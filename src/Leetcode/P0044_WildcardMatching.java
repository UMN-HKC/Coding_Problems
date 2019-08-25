package Leetcode;

public class P0044_WildcardMatching {

    // approach 1: DP
    // dp[i][j] =
    //     if s[i] == p[j] || p[j] == '?': dp[i][j] == dp[i-1][j-1]
    //     else if p[j] == '*': dp[i][j] = dp[i-1][j] || dp[i][j-1]
    //     else dp[i][j] = false


    public boolean isMatch_dp(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                dp[0][i+1] = true;
            }
            else {
                break;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || (p.charAt(j) == '?')) dp[i+1][j+1] = dp[i][j];
                else if (p.charAt(j) == '*') {
                    dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j];
                }
            }
        }
        return dp[m][n];
    }


    // approach 2: O(1) space approach
    // The basic idea is to denote the last encountered star index in p and index in s
    // at that time, so that next time when characters do not match, we can backtrack
    // to previous indices

    // time: O(m * n)
    // space: O(1)

    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, starIdx = -1, lastStarSi = 0;
        while (si < s.length()) {
            if ((si < s.length() && pi < p.length() && s.charAt(si) == p.charAt(pi)) ||
                    (pi < p.length() && p.charAt(pi) == '?')) {
                si++;
                pi++;
            }
            else if (pi < p.length() && p.charAt(pi) == '*') {
                starIdx = pi;
                lastStarSi = si;
                pi++;
            }
            else if (starIdx != -1) {
                pi = starIdx;
                si = ++lastStarSi;
            }
            else {
                return false;
            }
        }
        while (pi < p.length() && p.charAt(pi) == '*') pi++;
        return pi == p.length();
    }
}
