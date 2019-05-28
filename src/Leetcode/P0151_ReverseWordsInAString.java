package Leetcode;

public class P0151_ReverseWordsInAString {

    // my initial approach
    public String reverseWords_initial(String s) {
        if (s == null || s.length() < 1 || s.equals("")) return "";
        int itr = 0;
        String res = "";
        // skip trailing empty space
        while (itr < s.length() && s.charAt(itr) == ' ') {
            itr++;
        }
        while (itr < s.length()) {
            String newWord = "";
            while (itr < s.length() && s.charAt(itr) != ' ') {
                newWord = newWord + s.charAt(itr);
                itr++;
            }
            res = res.equals("") ? newWord + res : newWord + " " + res;
            while (itr < s.length() && s.charAt(itr) == ' ') {
                itr++;
            }
        }
        return res;
    }

    // approach 2: with lib functions

    public String reverseWords_lib_func(String s) {
        String[] parts = s.trim().split("\\s+");
        String out = "";
        for (int i = parts.length - 1; i > 0; i--) {
            out += parts[i] + " ";
        }
        return out + parts[0];
    }

    // approach 3: without using lib functions and is clean
    public String reverseWords_clean(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
}
