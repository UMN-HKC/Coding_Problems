package Leetcode;

import java.util.HashSet;
import java.util.Set;

public class P1044_LongestDuplicateSubstring {

    public static long modulus = Long.MAX_VALUE / 26;

    public String longestDupSubstring(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        int n = S.length();
        int l = 1, r = n;
        int idx = -1;
        while (l <= r) {
            int len = l + (r - l) / 2;
            int temp = search(len, S);
            if (temp == -1) {
                r = len - 1;
            }
            else {
                l = len + 1;
                idx = temp;
            }
        }
        return idx == -1 ? "" : S.substring(idx, idx + l - 1);

    }
    private int search(int len, String s) {
        Set<Long> set = new HashSet<>();
        long hash = 0;
        long p = 1;
        for (int i = 0; i < len; i++) {
            hash = (hash * 26 + s.charAt(i) - 'a') % modulus;
            p = (p * 26) % modulus;
        }
        set.add(hash);
        for (int i = 1; i <= s.length() - len; i++) {
            hash = (hash * 26 + (s.charAt(i + len - 1) - 'a')
                    - (s.charAt(i - 1) - 'a') * p) % modulus;
            if (hash < 0) {
                hash += modulus;
            }
            if (set.contains(hash)) {
                return i;
            }
            set.add(hash);
        }
        return -1;
    }
}
