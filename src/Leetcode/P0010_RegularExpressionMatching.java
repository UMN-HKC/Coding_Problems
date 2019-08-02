package Leetcode;

public class P0010_RegularExpressionMatching {

    // approach 1: dfs

    public boolean isMatch_1(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    public boolean isMatch(String s, int si, String p, int pi) {
        if (si > s.length() || pi > p.length()) return false;
        if (si == s.length()) {
            if (pi == p.length()) {
                return true;
            }
        }
        // if pi next is *
        if ((pi < p.length() - 1) && p.charAt(pi + 1) == '*') {
            // match 0 previous character
            if (isMatch(s, si, p, pi + 2)) {
                return true;
            }
            // match 1
            if ((si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) && isMatch(s, si + 1, p, pi)) {
                return true;
            }
            return false;
        }
        else {
            if (pi < p.length() && si < s.length()) {
                return (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') && isMatch(s, si+1, p, pi+1);
            }
            return false;
        }
    }

    // approach 2: dfs with memo
    // 0: not visited, 1: valid, 2-1: not valid
    public boolean isMatch_2(String s, String p) {
        int[][] dp = new int[s.length()+1][p.length()+1];
        return isMatch(dp, s, 0, p, 0);
    }
    public boolean isMatch(int[][] dp, String s, int si, String p, int pi) {
        if (si == s.length()) {
            if (pi == p.length()) {
                dp[si][pi] = 1;
                return true;
            }
        }
        if (dp[si][pi] == 1) return true;
        if (dp[si][pi] == -1) return false;
        // if pi next is *
        if ((pi < p.length() - 1) && p.charAt(pi + 1) == '*') {
            // match 0 previous character
            if (isMatch(dp, s, si, p, pi + 2)) {
                dp[si][pi] = 1;
                return true;
            }
            // match 1
            if ((si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')) && isMatch(dp, s, si + 1, p, pi)) {
                dp[si][pi] = 1;
                return true;
            }
        }
        // pi's next is not * or pi has no next
        else {
            if (pi < p.length() && si < s.length()) {
                dp[si][pi] =((s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.') && isMatch(dp, s, si+1, p, pi+1)) ? 1 : -1;
                return dp[si][pi] == 1;
            }
        }
        dp[si][pi] = -1;
        return false;
    }

    // approach 3: DP
    // dp[i][j] represents s[0,i] matches p[0, j]

    public boolean isMatch_3(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        // deal with pattern like "a*a*a*"
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1]  = true;
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                else if (p.charAt(j) == '*') {
                    // - ignore the character before '*'
                    // - include 1 character before '*'
                    // - include multiple character before '*'
                    if (s.charAt(i) != p.charAt(j - 1) && p.charAt(j - 1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j - 1];
                    }
                    else {
                        dp[i+1][j+1] = dp[i+1][j - 1] || dp[i+1][j] || dp[i][j+1];
                    }

                }
            }
        }
        return dp[m][n];
    }
}
