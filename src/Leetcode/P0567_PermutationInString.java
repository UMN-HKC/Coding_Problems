package Leetcode;

public class P0567_PermutationInString {

    // approach 1: sliding window

    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int[] freq = new int[26];
        for (char c : s1.toCharArray()) {
            freq[c - 'a']++;
        }
        int i = 0, j = 0, cnt = n1;
        while (j < n2) {
            // expand
            char c = s2.charAt(j++);
            if (--freq[c - 'a'] >= 0) {
                cnt--;
            }
            // when condition holds(all characters covered), shrink and get/update result
            // if possible (j - i + 1 == n1)
            while (i < j && cnt == 0) {
                if (++freq[s2.charAt(i++) - 'a'] > 0) {
                    cnt++;
                }
                // update or return result
                if (j - i + 1 == n1) {
                    return true;
                }
            }
        }
        return false;
    }
}
