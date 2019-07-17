package Leetcode;

public class P0005_LongestPalindromicSubstring {

    // approach 1:
    // the basic idea is that for each index, we try to extend and find a longer palindrome.

    public String longestPalindrome_1(String s) {
        if (s == null || s.length() < 2) return s;
        String lps = s.substring(0,1);
        for (int i = 0; i < s.length()-1; i++) {
            String s1 = extend(s, i, i);
            String s2 = extend(s, i, i+1);
            String longer = s1.length() > s2.length() ? s1 : s2;
            lps = lps.length() > longer.length() ? lps : longer;

        }
        return lps;
    }
    public String extend(String s, int l, int r) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            else {
                break;
            }
        }
        return s.substring(l+1, r);
    }

    // pass an array of size 2 to store left index and length of palindromes instead of returning strings

    public String longestPalindrome_2(String s) {
        if (s == null || s.length() < 2) return s;
        int[] info = new int[2];
        for (int i = 0; i < s.length()-1; i++) {
            extend(s, i, i, info);
            extend(s, i, i+1, info);
        }
        return s.substring(info[0], info[0] + info[1]);
    }
    public void extend(String s, int l, int r, int[] info) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            else {
                break;
            }
        }
        if (r - l - 1 > info[1]) {
            info[0] = l + 1;
            info[1] = r - l - 1;
        }
    }
}
