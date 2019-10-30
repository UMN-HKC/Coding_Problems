package Leetcode;

public class P0809_ExpressiveWords {

    // appraoch 1: two pointers

    // The basic idea is to keep two pointers points to each string. For each character in
    // two strings, if they are not equal, return false directly. Otherwise, we move them forward
    // two pointers points to two different characters. Now, we have the length of two
    // previous same characters. If they are not equal, AND len2 is greather than len1 or len1
    // is smaller than 3, return false. Otherwise, continue checking. After the while loop,
    // check if two pointers have both reached the end of strings.


    public int expressiveWords(String S, String[] words) {
        if (words == null || words.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            if (isExpressive(S, words[i])) {
                res++;
            }
        }
        return res;
    }
    public boolean isExpressive(String s, String cur) {
        if (cur == null ||cur.length() == 0) return false;
        int i = 0, j = 0;
        while (i < s.length() && j < cur.length()) {
            if (s.charAt(i) != cur.charAt(j)) return false;
            else {
                int l1 = i, l2 = j;
                while (l1 < s.length() && s.charAt(l1) == s.charAt(i)) l1++;
                while (l2 < cur.length() && cur.charAt(l2) == cur.charAt(j)) l2++;
                int len1 = l1 - i, len2 = l2 - j;
                if (len1 != len2 && (len1 < 3 || len2 > len1)) {
                    return false;
                }
                i = l1;
                j = l2;
            }
        }
        return i == s.length() && j == cur.length();
    }
}
