package Leetcode;

import java.util.List;

public class P0524_LongestWordInDictionaryThroughDeleting {

    // approach 1: two pointers
    // The basic idea is to iterate every word in the dictionary and for each word
    // in the dictionary, we will apply two pointers technique to check if the word
    // in the dictionary is a sub sequence in s, and keep track of the current length
    // of our result and once we match a larger length of subsequence, we directly update
    // the result. If the length is the same, we store the lexicographical smaller one.

    // time: O(n * k)

    public String findLongestWord(String s, List<String> d) {
        int max = Integer.MIN_VALUE;
        String res = "";
        for (String word : d) {
            int i = 0, j = 0;
            while (i < s.length() && j < word.length()) {
                if (s.charAt(i) == word.charAt(j)) {
                    i++;
                    j++;
                }
                else {
                    i++;
                }
            }
            while (j == word.length() && i < s.length()) i++;
            if (i == s.length() && j == word.length()) {
                if (max == word.length()) {
                    res = res.compareTo(word) < 0 ? res : word;
                }
                else if (word.length() > max) {
                    max = word.length();
                    res = word;
                }
            }
        }
        return res;
    }
}
