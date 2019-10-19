package Leetcode;

public class P0647_PalindromicSubstrings {

    // approach 1: DP
    // dp[i][j] = dp[i+1][j - 1] && s[i] == s[j]

    // time: O(n^2)
    // space: O(n^2)

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = s.length();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                dp[i][i + len] = s.charAt(i) == s.charAt(i + len) && ((len == 1) || (dp[i + 1][i + len - 1]));
                res += dp[i][i + len] ? 1 : 0;
            }
        }
        return res;
    }

    // approach 2: two pointers

    // time: O(n^2)
    // space: O(1)

    public int countSubstrings_2(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] res = {0};
        for (int i = 0; i < s.length(); i++) {
            extend(s, i, i, res);
            extend(s, i, i + 1, res);
        }
        return res[0];
    }
    public void extend(String s, int l, int r, int[] res) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
            res[0]++;
        }
    }
}
