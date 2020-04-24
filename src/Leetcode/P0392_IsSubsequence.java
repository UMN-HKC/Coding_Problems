package Leetcode;

import java.util.*;

public class P0392_IsSubsequence {

    // approach 1: two pointers

    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        if (t == null || t.length() == 0) return false;
        int i = 0, j = 0;
        int sLen = s.length(), tLen = t.length();
        while (i < sLen && j < tLen) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == sLen;
    }

    // approach 2: binary search (for the follow-up)

    public boolean isSubsequence_2(String s, String t) {
        if (s == null || s.length() == 0) return true;
        if (t == null || t.length() == 0) return false;
        Map<Character, List<Integer>> map = new HashMap<>();
        // store character positions in t to our map
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        int curIdx = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) return false;
            List<Integer> listOfIndices = map.get(c);
            // find the first index that's greater than current idx in t
            int idx = binarySearch(listOfIndices, 0, listOfIndices.size() - 1, curIdx);
            if (idx == -1 || idx < curIdx) {
                return false;
            }
            curIdx = idx;
        }
        return true;
    }
    private int binarySearch(List<Integer> list, int l, int r, int target) {
        while (l < r) {
            int m = l + (r - l ) / 2;
            if (list.get(m) > target) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return list.get(l) > target ? list.get(l) : -1;
    }
}
