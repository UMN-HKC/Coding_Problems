package Leetcode;

public class P0097_InterleavingString {


    // dp[i][j] means whether or not s3[0,i+j-1] is interleaving of s1[0,i-1] and s2[0,j-1]

    // example: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    //      Ø d b b c a
    //    Ø T F F F F F
    //    a T F F F F F
    //    a T T T T T F
    //    b F T T F T F
    //    c F F T T T T
    //    c F F F T F T

    public boolean isInterleave(String s1, String s2, String s3) {
        // if length is not equal, directly return false since it's not possible to form
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++) {
            // remember to add dp[i - 1][0]
            dp[i][0] = dp[i-1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i < dp[0].length; i++) {
            // remember to add dp[0][i - 1]
            dp[0][i] = dp[0][i-1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                // the interleaving dp[i][j] either comes from (s1 when the current character
                // in s1 matches the current character in s3 and previous matching of s1 is
                // successful) or (s2 when the current character in s1 matches the current character
                // in s3 and previous matching of s1 is successful)
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                        || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
