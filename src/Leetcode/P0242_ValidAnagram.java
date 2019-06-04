package Leetcode;

public class P0242_ValidAnagram {

    // initial approach

    // time: O(n)
    // space: O(1)

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            chars[sc - 'a']++;
            chars[tc - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (chars[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
