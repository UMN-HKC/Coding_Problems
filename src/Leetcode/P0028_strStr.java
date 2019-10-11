package Leetcode;

public class P0028_strStr {

    // approach 1: brute force

    public int strStr_initial(String haystack, String needle) {
        if (needle == null || needle == "") {
            return 0;
        }
        int itr = 0;
        while (itr <= haystack.length() - needle.length()) {
            int start = itr;
            int needleItr = 0;
            while (itr <= haystack.length() - needle.length() && needleItr < needle.length()
                    && haystack.charAt(start) == needle.charAt(needleItr)) {
                start++;
                needleItr++;
            }
            if (needleItr == needle.length()) {
                return itr;
            }
            itr++;
        }
        return -1;
    }

    // more concise

    public int strStr_concise(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    // approach 2: KMP
    // link: https://www.youtube.com/watch?v=BXCEFAzhxGY&t=935s

    // The basic idea is that we will create a memo array to prevent us from
    // going all the way back to begging of needle string when characters don't
    // match between haystack and needle.
    // memo[i]: where to start matching in needle after a mismatch at i + 1

    // time: O(m + n)
    // space: O(n)

    public int strStr_kmp(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int[] memo = KMP(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            else if (j > 0) {
                j = memo[j - 1];
            }
            else {
                i++;
            }
        }
        return j == needle.length() ? i - needle.length() : -1;
    }
    public int[] KMP(String s) {
        int[] memo = new int[s.length()];
        int i = 0, j = 1;
        while (j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                memo[j] = i + 1;
                i++;
                j++;
            }
            else if (i > 0) {
                i = memo[i - 1];
            }
            else {
                j++;
            }
        }
        return memo;
    }
}

