package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0290_WordPattern {

    // idea inspired by lc205(Isomorphic String) ---- checking the previous index of the current visiting
    // characters in both string, if they do not equal to each other, it means the pattern is already broken

    // time: O(n)
    // space: O(n)

    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null || str.length() == 0 || pattern.length() == 0) {
            return false;
        }
        Map<String, Integer> map = new HashMap<>();
        int[] chars = new int[26];

        String[] words = str.trim().split(" ");
        if (words.length != pattern.length()) return false;
        for (int i = 0; i < pattern.length(); i++) {
            if (map.containsKey(words[i])) {
                if (map.get(words[i]) != chars[pattern.charAt(i) - 'a'])
                    return false;
            }
            else {
                // when patterns has appeared before, but matches with a new word string
                if (chars[pattern.charAt(i) - 'a'] != 0) {
                    return false;
                }
                else {
                    // it is necessary to increment index by 1 to avoid 0 index
                    // for example:
                    // Input:pattern = "abba", str = "dog cat cat fish"
                    // Output: false
                    // in here, 'a' and "dog" both have index 0 as its previous index when we
                    // encounter 'a' again
                    map.put(words[i], i+1);
                    chars[pattern.charAt(i) - 'a'] = i+1;
                }
            }
        }
        // str matches all pattern characters
        return true;
    }
}
