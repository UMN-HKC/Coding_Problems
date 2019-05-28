package Leetcode;

public class P0014_LongestCommonPrefix {
    public String longestCommonPrefix_initial_bruteforce(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String lcpStr = strs[0];
        int lcp = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            lcp = lcpHelper(lcp, lcpStr, strs[i]);
            if (lcp == 0) {
                return "";
            }
        }
        return lcpStr.substring(0, lcp);
    }
    public int lcpHelper(int lcp, String lcpStr, String s2) {
        int l1 = 0, l2 = 0;
        while (l1 < lcpStr.length() && l2 < s2.length()
                && lcpStr.charAt(l1) == s2.charAt(l2) && l1 < lcp) {
            l1++;
            l2++;
        }
        return Math.min(lcp, l2);
    }
}
