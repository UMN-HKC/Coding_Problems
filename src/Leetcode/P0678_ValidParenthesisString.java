package Leetcode;

public class P0678_ValidParenthesisString {

    // link: https://www.youtube.com/watch?v=h9Y3i7hhCpo&t=175s

    // approach 1: brute force recursion

    // time: O(3^n)
    // space: O(n)

    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) return true;
        return check(s, 0, 0);
    }
    public boolean check(String s, int idx, int cnt) {
        if (cnt < 0) return false;
        if (idx == s.length()) {
            return cnt == 0;
        }
        char c = s.charAt(idx);
        if (c == '(') {
            return check(s, idx + 1, cnt + 1);
        }
        else if (c == ')') {
            return check(s, idx + 1, cnt - 1);
        }
        else {
            return check(s, idx + 1, cnt + 1) || check(s, idx + 1, cnt - 1) || check(s, idx + 1, cnt);
        }
    }

    // approach 2: bottom-up DP

    // dp[i][j] = true, only if either of the following two cases is satisfied:
    //   - dp[i+1][j-1] && (s.charAt(i) == '(' || s.charAt(i) == '*') && (s.charAt(j) == ')' || s.charAt(j) == '*')
    //   - dp[i][k] && dp[k+1][j], for i <= k < j.
    // for example: "(((*)))" could be an example for case1 and "()()()" could be an example for case2

    // time: O(n^3)
    // space: O(n^2)

    public boolean checkValidString_2(String s) {
        if (s == null || s.length() == 0) return true;
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = s.charAt(i) == '*';
        }
        for (int length = 2; length <= len; length++) {
            for (int i = 0; i <= len - length; i++) {
                int j = i + length - 1;
                if (((s.charAt(i) == '(' || s.charAt(i) == '*')
                        && (s.charAt(j) == ')' || s.charAt(j) == '*')) && (length == 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    continue;
                }

                for (int k = i + 1; k < j - 1; k++) {
                    if (dp[i][j] || (dp[i][k] && dp[k + 1][j])) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        return dp[0][len - 1];
    }

    // approach 3: minOp,maxOp

    // time: O(n)
    // space: O(1)

    // minOp is the required(must be matched) number of open parentheses
    // maxOp is the maximum number of open parentheses we have in current strp
    // we need to make sure maxOp is always greater or equal to 0, which means
    // we do not have extra non-matched close parentheses at current step
    // note that once minOp is less than 0, we reset it to 0

    // In the end, we need to ensure that minOp is 0 which means all mist-be-matched
    // open parentheses are matched and maxOp is greater or equal to 0 which means
    // there's no extra close parentheses.

    public boolean checkValidString_3(String s) {
        if (s == null || s.length() == 0) return true;

        int maxOp = 0, minOp = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minOp++;
            }
            else {
                minOp--;
            }
            if (minOp < 0) minOp = 0;
            if (c != ')') {
                maxOp++;
            }
            else {
                maxOp--;
            }
            if (maxOp < 0) return false;
        }
        return minOp == 0 && maxOp >= 0;
    }

}
