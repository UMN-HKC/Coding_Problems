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
    // https://leetcode.com/problems/valid-parenthesis-string/discuss/107577/Short-Java-O(n)-time-O(1)-space-one-pass

    // time: O(n)
    // space: O(1)

    // Imagine this as a tree, when you encounter '(' or ')', there is only possibility so parent only has one child
    // but when you see '*', there are 3 possibilities so parent has 3 children. Now, when you reach the left node
    // of the tree (going through all possible paths), you end up with a count. If this count is 0 then it means
    // along the way * has somehow added/cancelled brackets to satisfy the string.
    // There's one thing to notice here, if you see after every step, the outcomes are consecutive (ex: 1,2,3,4 or 0,1,2,3 and so on)
    //
    // In second part he used the findings from the first part and is only tracking the lower and higher value of those consecutive values.
    // When you see '(', you update lo and hi by 1, which basically means you're adding 1 to the entire series.
    // When you see ')', you update lo to lo-- (when lo > 0 to avoid going negative)and hi to hi--, which basically means you're subtracting 1 from the entire series.
    // When you see anything else (indirectly '*'), you have to expand your outcomes. So, you decrement lo to lo-- (when lo > 0 to avoid error)(adding one number on the left of the series) and update hi and hi++ (adding one number on right of series).
    // If your hi gets < 0 that means you cannot balance it uptil now, so it doesn't matter what the rest of the string is and you return false.
    // At the end you make sure your low == 0 meaning you've reached the beginning of the string.

    public boolean checkValidString_3(String s) {
        if (s == null || s.length() == 0) return true;
        int minOp = 0;
        int maxOp = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minOp++;
                maxOp++;
            }
            else if (c == ')') {
                if (minOp > 0) minOp--;
                maxOp--;
                if (maxOp < 0) return false;
            }
            else {
                if (minOp > 0) minOp--;
                maxOp++;
            }
        }
        return minOp == 0;
    }

}
