package Leetcode;

public class P0072_EditDistance {

    // approach 1: DP

    // dp[i][j] means the minimum operations taken to convert substring word1[0,i] to substring of word2[0,j]

    //   - dp(i - 1, j) represents delete operation    "(ben)y" -> "(eph)"
    //   - dp(i, j - 1) represents insert operation    "(beny)" -> "(ep)h"
    //   - dp(i - 1, j - 1) represents replace operation    "(ben)y" -> "(ep)h"
    //   - when characters are the same dp[i][j] = dp[i-1][j-1]

    public int minDistance(String word1, String word2) {
        if (word1 == null || word1.length() == 0) return word2.length();
        if (word2 == null || word2.length() == 0) return word1.length();
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1+1][l2+1];
        for (int i = 0; i < dp.length; i++) dp[i][0] = i;
        for (int i = 0; i < dp[0].length; i++) dp[0][i] = i;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i-1][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[l1][l2];
    }

    // optimize space from O(m * n) to O(n)

    public int minDistance_2(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[] pre = new int[len2 + 1];
        int[] cur = new int[len2 + 1];
        for (int i = 1; i < pre.length; i++) pre[i] = i;
        for (int i = 0; i < len1; i++) {
            // note that we need to initialize cur[0] to i + 1 because that is
            // how we set the base case when we have rows in approach 1
            cur[0] = i + 1;
            for (int j = 0; j < len2; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    cur[j + 1] = pre[j];
                }
                else {
                    cur[j + 1] = 1 + Math.min(Math.min(pre[j + 1], cur[j]), pre[j]);
                }
            }
            int[] temp = pre;
            pre = cur;
            cur = temp;
        }
        return pre[len2];
    }

}
