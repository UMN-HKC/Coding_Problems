package Leetcode;

public class P0115_DistinctSubsequences {

    // approach 1: DP
    // dp[i][j]: number of distinct subsequences we could pick characters from s[0, j]
    // to form t[0, i].

//    lets see if T[i] != S[j]
//    then we still have to find the entire T in a subset of S[j-1] hence it will be listed as
//    dp[i,j] = dp[i,j-1] // i.e. ignore the jth character in S.
//    now if T[i] == S[j] it means we have a choice, either we take the jth character to find the
//    entire T or we do not take the jth character to find the entire T.
//    If we take the jth character - that means now we have to find a solution to T[i-1] (because T[i] == S[j]))
//    If we do not take the jth character - that means now we have to find a solution to T[i] from S[j-1] (not taking the jth character).
//    The total number of permutations would be = permutations with considering the jth character + permutations without considering the jth character.
//    Hence in this case dp[i,j] = dp[i-1,j-1] + dp[i,j-1].

    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i < dp.length; i++) dp[i][0] = 0;
        for (int i = 0; i < dp[0].length; i++) dp[0][i] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(j - 1) != t.charAt(i - 1)) {
                    dp[i][j] = dp[i][j - 1];
                }
                else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
