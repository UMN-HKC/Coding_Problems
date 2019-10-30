package Leetcode;

import java.util.Arrays;

public class P1170_CompareStringsByFrequencyOfTheSmallestCharacter {

    // approach 1: binary search

    // The basic idea is to apply binary search to find the position where the frequency
    // at that position is the first position that's greater than the query frequency

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] freq = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            freq[i] = f(words[i]);
        }
        Arrays.sort(freq);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int cur = f(queries[i]);
            int l = 0, r = freq.length - 1;
            while (l <= r)  {
                int m = l + (r - l) / 2;
                if (freq[m] <= cur) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
            res[i] = freq.length - r - 1;
        }
        return res;
    }
    public int f(String word) {
        int[] freq = new int[26];
        char lo = 'z';
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c < lo) {
                lo = c;
            }
            freq[c - 'a']++;
        }
        return freq[lo - 'a'];
    }
}
