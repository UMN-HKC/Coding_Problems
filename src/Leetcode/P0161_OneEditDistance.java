package Leetcode;

public class P0161_OneEditDistance {

    // couldn't solve it initially
    // idea borrowed from discussion board: two pointers
    // important to simply realize after 1 edit distance, the rest of strings should be same

    public boolean isOneEditDistance(String s, String t) {
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        if (s.length() == 0 && t.length() == 1) return true;
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) != t.charAt(j)) {
                if (s.length() < t.length()) {
                    return s.substring(i).equals(t.substring(j+1));
                }
                else {
                    return s.substring(i+1).equals(t.substring(j+1));
                }
            }
            i++;
            j++;
        }
        // if no edit distance done earlier, check if it would be delete operation on the last index
        return j == t.length() - 1;
    }
}
