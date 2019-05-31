package Leetcode;

import java.util.HashSet;
import java.util.Set;

public class P0318_MaxProductWordLength {

    // initial approach: brute force

    // time: O((n^2) * (average length of a word))
    // space: O(average length of a word)

    public int maxProduct_initial(String[] words) {
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (!share(words[i], words[j])) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
    public boolean share(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        for (char c : s1.toCharArray()) {
            set.add(c);
        }
        for (char c : s2.toCharArray()) {
            if (set.contains(c)) {
                return true;
            }
        }
        return false;
    }

    // approach 2: use bit manipulation to speed up the checking of same character in two strings

    // time: O(n^2)
    // space O(n)

    public int maxProduct_bit(String[] words) {
        int[] bits = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (char c : word.toCharArray()) {
                bits[i] |= 1 << (c - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((bits[i] & bits[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
