package Leetcode;

public class P0125_ValidPalindrome {

    // two pointers

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int i = 0, j = s.length() - 1;
        s = s.toLowerCase();
        while (i < j) {
            // skip non-alphanumeric characxters
            while (i < j && (!(s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
                    && !(s.charAt(i) >= '0' && s.charAt(i) <= '9'))) i++;
            while (i < j && (!(s.charAt(j) >= 'a' && s.charAt(j) <= 'z')
                    && !(s.charAt(j) >= '0' && s.charAt(j) <= '9'))) j--;
            // return false directly if not equal
            if (i < j && s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
