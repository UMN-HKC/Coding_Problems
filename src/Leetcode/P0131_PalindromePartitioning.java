package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0131_PalindromePartitioning {

    // approach 1: backtracking

    // Since we are required to find all possible palindrome partitioning, it
    // reminds of applying backtracking. The basic idea is to for each length of
    // the substring, check if it is a palindrome, if it is, we recursively call
    // on the rest of the string, and once done backtrack to previous state to explore
    // other possibilities, aka other length of substring as first word.

    // time: O(n * (2^n))

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        List<String> list = new ArrayList<>();
        backtrack(res, list,  s);
        return res;
    }
    public void backtrack(List<List<String>> res, List<String> list,  String s) {
        if (s == null || s.length() == 0) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            String first = s.substring(0, i+1);
            if (isPalindrome(first)) {
                list.add(first);
                backtrack(res, list,  s.substring(i+1));
                list.remove(list.size() - 1);
            }
        }
    }
    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    // approach 2:
    // backtracking with a preprocessed boolean DP array to help us determine
    // if a string is a palindrome in O(1) while backtracking
    // dp[i][j]: represents s[i, j] is a palindrome

    // time: O(2^n)

    public List<List<String>> partition_2(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        List<String> list = new ArrayList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        // O(n^2)
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                // only when the two characters on two sides are same and substring
                // between these two characters are palindrome, can we set dp[j][i] to true
                dp[j][i] = (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1]));
            }
        }
        backtrack(res, list, dp, s, 0);
        return res;
    }
    public void backtrack(List<List<String>> res, List<String> list, boolean[][] dp,  String s, int index) {
        if (index >= s.length()) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (dp[index][i]) {
                list.add(s.substring(index, i+1));
                backtrack(res, list, dp, s, i+1);
                list.remove(list.size() - 1);
            }
        }
    }
}
