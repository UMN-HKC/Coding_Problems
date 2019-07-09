package Leetcode;
import java.util.*;

public class P0395_LongestSubstringWithAtLeastKRepeatingCharacters {

    // The idea is to apply sliding window methodology.
    // Basically, we will check that, with n([1~26]) unique characters, what is the longest substring
    // we can get where all these n unique characters have at least k repeating characters.

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        // unique: how many unique characters we currently have for our substring
        // noLessThanK: number of unique characters whose frequency(cnt) is not less than k
        // max: result
        int i, j, num, unique, noLessThanK, max = 0;
        int[] cnt = new int[124];

        for (num = 1; num <= 26; num++) {
            i = j = unique = noLessThanK = 0;
            Arrays.fill(cnt, 0);
            while (j < s.length()) {
                // move right pointer when we haven't covered enough unique characters
                // note that in here we use unique <= num because we still want to expand
                // our search when unque == num
                if (unique <= num) {
                    char c = s.charAt(j);
                    if (cnt[c] == 0) unique++;
                    if (++cnt[c] == k) noLessThanK++;
                    j++;
                }
                // move left pointer when we have more unique characters than we need
                else {
                    char c = s.charAt(i);
                    if (cnt[c] == k) noLessThanK--;
                    if (--cnt[c] == 0) unique--;
                    i++;
                }
                // update res when all conditions are satisfied
                if (unique == num && noLessThanK == unique) max = Math.max(max, j - i);
            }
        }
        return max;
    }
}
