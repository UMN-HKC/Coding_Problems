package Leetcode;

import java.util.*;

public class P1055_ShortestWayToFormString {

    // approach 1: two pointers + greedy
    // one pointers iterate target string and one pointer iterates source string.
    // Try to match more target string using source string during each iteration
    // if within one iteration, target string pointer does not move, it means the
    // current character in the target string does not exist in the source string.

    // time: O(m * n)

    public int shortestWay(String source, String target) {
        int t = 0;
        int res = 0;
        while (t < target.length()) {
            int pre = t;
            for (int i = 0; t < target.length() && i < source.length(); i++) {
                if (source.charAt(i) == target.charAt(t)) {
                    t++;
                }
            }
            if (t == pre) return -1;
            res++;
        }
        return res;
    }

    // approach 2: inverted index + binary search
    // link: https://leetcode.com/problems/shortest-way-to-form-string/discuss/304662/Python-O(M-%2B-N*logM)-using-inverted-index-%2B-binary-search-(Similar-to-LC-792)

    // The basic idea is to improve search on source string by applying inverted index and
    // binary search. Basically, we use a lookup array to store each source string character
    // indices as a list. When we need a character from the target string, instead of doing
    // a linear search, we perform a binary search. Note that, we will keep a lastIdx variable
    // to indicate the next starting index position for searching, because we are not resetting
    // yet, and our goal will be finding the first element that is greater or equal to this
    // lastIdx.

    // time: O(m + n * log(m))

    public int shortestWay_2(String source, String target) {
        List<Integer>[] lookup = new ArrayList[26];
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (lookup[c - 'a'] == null) {
                lookup[c - 'a'] = new ArrayList<>();
            }
            lookup[c - 'a'].add(i);
        }
        int cnt = 1;
        int i = 0;
        int lastIdx = 0;
        while (i < target.length()) {
            char c = target.charAt(i);
            if (lookup[c - 'a'] == null) return -1;
            lastIdx = binarySearch(lookup[c - 'a'], lastIdx);
            if (lastIdx == -1) {
                cnt++;
                lastIdx = lookup[c - 'a'].get(0);
            }
            else {
                i++;
                lastIdx++;
            }
        }
        return cnt;
    }
    public int binarySearch(List<Integer> arr, int idx) {
        if (idx < 0) return arr.get(0);
        if (idx > arr.get(arr.size() - 1)) return -1;
        int l = 0, r = arr.size() - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (arr.get(m) == idx) return arr.get(m);
            else if (arr.get(m) < idx) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return arr.get(l);
    }
}
