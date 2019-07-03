package Leetcode;

public class P0076_MinimumWindowSubstring {

    // couldn't figure out initially, idea borrowed from discussion board
    // Classic sliding window approach: use two pointers, right pointer starts
    // covering elements until we satisfy some condition. Then, the left pointer
    // starts to exclude elements (and meanwhile updates result) until we breaks the condition

    public String minWindow(String s, String t) {
        int[] chars = new int[128];
        for (char c : t.toCharArray()) {
            chars[c]++;
        }
        int i = 0, j = 0, minStart = 0, cnt = t.length(), d = Integer.MAX_VALUE;
        while (j < s.length()) {
            // covering characters but decrementing their count in array
            if (chars[s.charAt(j++)]-- > 0) {
                cnt--;
            }
            // once all target characters are covered (when cnt == 0), we start
            // moving the left pointer to exclude characters
            while (cnt == 0) {
                // update d if shorter range found
                if (j - i < d) {
                    d = j - i;
                    minStart = i;
                }
                // if after incrementing their count, count becomes greater than 0
                // it means this is the target character that we just excluded, after which
                // we will exit the inner while loop and start moving right pointer again
                if (++chars[s.charAt(i++)] > 0) {
                    cnt++;
                }
            }
        }
        // be careful that d is 1 greater than the actual (j - i)
        return d == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart+d);
    }
}
