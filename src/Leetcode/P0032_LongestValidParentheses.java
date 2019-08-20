package Leetcode;

public class P0032_LongestValidParentheses {

    // approach 1: DP
    // we can use an array to record the longest valid parentheses ending at each
    // index. So that later indices could refer to its previous result. So it is
    // a DP problem. And the transition function is as follows:
    // if s[i] == '(': longest[i] = 0
    // if s[i] == ')':
    //      - if s[i - 1] == '(': longest[i] = 2 + longest[i - 2]
    //      - if s[i - 1] == ')': longest[i] = 2 + longest[i - 1] + longest[i - longest[i - 1] - 2]

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int[] longest = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    longest[i] = 2 + (i - 2 >= 0 ? longest[i - 2] : 0);
                }
                else {
                    if (i - longest[i - 1] - 1 >= 0 && s.charAt(i - longest[i - 1] - 1) == '(') {
                        longest[i] = 2 + longest[i - 1] + (i - longest[i - 1] - 2 >= 0 ? longest[i - longest[i - 1] - 2] : 0);
                    }

                }
                max = Math.max(longest[i], max);
            }
        }
        return max;
    }
}
