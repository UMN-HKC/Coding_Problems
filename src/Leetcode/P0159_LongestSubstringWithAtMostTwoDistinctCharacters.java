package Leetcode;

public class P0159_LongestSubstringWithAtMostTwoDistinctCharacters {

    // sliding window

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] cnt = new int[127];
        int distinct = 0, i = 0, j = 0, max = 0;
        while (j < s.length()) {
            // try to satisfy the condition and expand
            if (distinct <= 2) {
                if (cnt[s.charAt(j)] == 0) {
                    distinct++;
                }
                cnt[s.charAt(j)]++;
                j++;
            }
            // shrink the window and try to re-satisfy the condition
            else {
                cnt[s.charAt(i)]--;
                if (cnt[s.charAt(i)] == 0) {
                    distinct--;
                }
                i++;
            }
            // update result
            if (distinct <= 2) max = Math.max(max, j - i);
        }
        return max;
    }
}
