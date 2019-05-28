package Leetcode;

public class P0383_RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] mag = new int[26];
        for (char c : magazine.toCharArray()) {
            mag[c - 'a']++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--mag[ransomNote.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
