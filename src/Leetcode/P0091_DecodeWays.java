package Leetcode;

public class P0091_DecodeWays {

    // approach 1: DP
    public int numDecodings_dp(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            int ones = s.charAt(i - 1) - '0';
            dp[i] += ones != 0 ? dp[i - 1] : 0;
            if (i == 1) continue;
            int twos = Integer.valueOf(s.substring(i-2, i));
            dp[i] += twos >= 10 && twos <= 26 ? dp[i - 2] : 0;
        }
        return dp[dp.length - 1];
    }

    // optimize space from O(n) to O(1)
    public int numDecodings_dp2(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int prev1 = 1, prev2 = 1;
        int tot = 1;
        for (int i = 1; i < s.length(); i++) {
            tot = 0;
            int ones = s.charAt(i) - '0';
            int twos = Integer.valueOf(s.substring(i-1, i+1));
            tot += ones != 0 ? prev1 : 0;
            tot += twos >= 10 && twos <= 26 ? prev2 : 0;
            prev2 = prev1;
            prev1 = tot;
        }
        return tot;
    }
}
