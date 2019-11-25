package Leetcode;

public class P0010_RegularExpressionMatching {

    // approach 1: DP
    // dp[i][j] represents s[0,i] matches p[0, j]

    public boolean isMatch_3(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 2; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*' && dp[0][i - 2]) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else if (p.charAt(j - 1) == '*') {
                    // '*' act as previous pattern character only when the previous
                    // pattern character is the could match the current input character
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                    // pattern character eliminate the previous character
                    // or act as no character
                    dp[i][j] = dp[i][j] || dp[i][j - 2] || dp[i][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    // approach 2: dfs

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

    // approach 3: dfs with memo
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
}
