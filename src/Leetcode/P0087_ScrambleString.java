package Leetcode;

public class P0087_ScrambleString {

    // couldn't figure out initially and idea borrowed from discussion board
    // recursion approach: the basic idea is to realize that we can compare
    // letter frequency between two substring and see if they have the same
    // frequency, they are scramble strings
    // for example, there are two cases to consider:
    // "gr|eat" "rg|tae", and another case, "a|bc" "bc|a"


    // time: factorial
    // T(n) = (n-1)*T(n-1) + n
    // space: O(n) stack depth

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int[] freq = new int[126];

        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 126; i++) {
            if (freq[i] != 0) return false;
        }
        for (int i = 1; i < s1.length(); i++) {
            // normal order
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            // reverse order
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }
        return false;
    }

}
