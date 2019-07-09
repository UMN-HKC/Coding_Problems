package Leetcode;

public class P0003_LongestSubstriungWithoutRepeatingCharacters {

    // sliding window approach

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 1, i = 0, j = 0;

        // here we use int array of size 127 to include all ascii characters
        // but we can use set instead to be more general
        int[] cnt = new int[127];
        while (j < s.length()) {
            // try to expand if condition allows
            if (cnt[s.charAt(j)] == 0) {
                cnt[s.charAt(j)]++;
                j++;
            }
            // shrink to resatisfy condition
            else {
                cnt[s.charAt(i)]--;
                i++;
            }
            // update
            if (j - i > max) {
                max = j - i;
            }
        }
        return max;
    }
}
