package Leetcode;

public class P0844_BackspaceStringCompare {

    // approach 1: two pointers

    public boolean backspaceCompare(String S, String T) {
        int d1 = 0;
        int d2 = 0;
        int i = S.length() - 1;
        int j = T.length() - 1;
        while (i >= 0 || j >= 0) {
            // skip when there's delete available
            while (i >= 0 && (S.charAt(i) == '#' || d1 > 0)) {
                if (S.charAt(i) == '#') {
                    d1++;
                }
                else {
                    d1--;
                }
                i--;
            }
            // skip when there's delete available
            while (j >= 0 && (T.charAt(j) == '#' || d2 > 0)) {
                if (T.charAt(j) == '#') {
                    d2++;
                }
                else {
                    d2--;
                }
                j--;
            }
            // check two characters equality
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))  return false;
            i--;
            j--;
        }
        return i == j;
    }
}
