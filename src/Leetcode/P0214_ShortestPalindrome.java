package Leetcode;

public class P0214_ShortestPalindrome {

    // approach 1: brute force

    public String shortestPalindrome_1(String s) {
        StringBuilder sb = new StringBuilder();
        for (int add = 0; add < s.length(); add++){
            if (check(s, add)) {
                return sb.append(s.substring(s.length() - add)).reverse().append(s).toString();
            }
        }
        return sb.append(s).reverse().append(s).toString();
    }
    public boolean check(String s, int add) {
        int l = 0, r = s.length() - add - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    // approach 2: KMP algo
    // link: https://leetcode.com/problems/shortest-palindrome/discuss/60113/Clean-KMP-solution-with-super-detailed-explanation

    // So basically the first step is that we can convert the problem to
    // "find the longest palindrome substring starting at index 0".
    // And then maybe after a little bit further digging, we can know that
    // if we attach the original string's reversed form to the end of the
    // original string, the problem can be further converted to "find the
    // longest prefix substring that matches its suffix substring". But to
    // differentiate original string and its reversed string, we insert any
    // special character between them in the combined string, this is to
    // reset the value in the KMP lookup table.

    public String shortestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        String rev = sb.reverse().toString();
        int[] memo = KMP(s + "#" + rev);
        return rev.substring(0, rev.length() - memo[memo.length - 1]) + s;
    }
    public int[] KMP(String s) {
        int[] memo = new int[s.length()];
        int i = 0, j = 1;
        while (j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                memo[j] = i + 1;
                i++;
                j++;
            }
            else if (i > 0) {
                i = memo[i - 1];
            }
            else {
                j++;
            }
        }
        return memo;
    }

}
