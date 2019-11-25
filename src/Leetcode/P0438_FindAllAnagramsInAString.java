package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0438_FindAllAnagramsInAString {

    // approach 1: sliding window

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() < p.length()) return res;
        int[] freq = new int[26];
        int cnt = 0;
        for (char c : p.toCharArray()) {
            cnt++;
            freq[c - 'a']++;
        }
        int l = 0, r = 0;
        while (r < s.length()) {
            if (--freq[s.charAt(r++) - 'a'] >= 0) cnt--;
            if (r - l > p.length()) {
                if (++freq[s.charAt(l++) - 'a'] > 0) cnt++;
            }
            if (cnt == 0) {
                res.add(l);
            }
        }
        return res;
    }
}
