package Leetcode;

public class P0340_LongestSubstringWithAtMostKDistinctCharacters {

    // sliding window

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int[] cnt = new int[127];
        int i = 0, j = 0, distinct = 0, max = 0;
        while (j < s.length()) {

            // try to expand when condition is satisfied
            if (distinct <= k) {
                if (cnt[s.charAt(j)] == 0) {
                    distinct++;
                }
                cnt[s.charAt(j)]++;
                j++;
            }
            // shrink and try to re-satisfy the condition
            else {
                if (cnt[s.charAt(i)] == 1) {
                    distinct--;
                }
                cnt[s.charAt(i)]--;
                i++;
            }

            // update result when condition is satisfied
            if (distinct <= k) max = Math.max(max, j - i);
        }
        return max;
    }
}
