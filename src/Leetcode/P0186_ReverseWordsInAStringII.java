package Leetcode;

public class P0186_ReverseWordsInAStringII {
    public void reverseWords(char[] str) {
        if (str == null || str.length == 0) {
            return;
        }
        // reverse the whole array
        reverse(str, 0, str.length - 1);
        int itr = 0;
        while (itr < str.length) {
            // skip to non-space
            int start = itr;
            while (itr < str.length && str[itr] != ' ') {
                itr++;
            }
            // reverse a word. This also deals with the last word
            reverse(str, start, itr-1);
            itr++;
        }
        return;
    }
    public void reverse(char[] str, int l, int r) {
        while (l < r) {
            char temp = str[l];
            str[l] = str[r];
            str[r] = temp;
            l++;
            r--;
        }
        return;
    }
}
