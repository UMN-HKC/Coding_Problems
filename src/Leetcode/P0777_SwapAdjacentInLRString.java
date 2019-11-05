package Leetcode;

public class P0777_SwapAdjacentInLRString {

    // approach 1: two pointers

    // The basic idea is to make sure the following 3 criteria are satisfied:
    // - "L" and "R" in both start and end strings are in same order and same number
    // - the respective indices of "R" are smaller in start than that in end, because "R" moves to the right
    // - the respective indices of "L" are greater in start than that in end, because "L" moves to the left

    public boolean canTransform(String start, String end) {
        int i = 0, j = 0;
        while (i < start.length() || j < end.length()) {
            // skip to non-X characters for pointers of both start and end strings
            while (i < start.length() && start.charAt(i) == 'X') i++;
            while (j < end.length() && end.charAt(j) == 'X') j++;
            if (i < start.length() && j < end.length()) {
                if (start.charAt(i) == 'L' && end.charAt(j) == 'R' && i <= j) {
                    return false;
                }
                else if (start.charAt(i) == 'R' && end.charAt(j) == 'L' && i >= j) {
                    return false;
                }
                else if (start.charAt(i) == 'L' && end.charAt(j) == 'L' && i < j) {
                    return false;
                }
                else if (start.charAt(i) == 'R' && end.charAt(j) == 'R' && i > j) {
                    return false;
                }
                i++;
                j++;
            }
            else if (i == start.length()) {
                while (j < end.length() && end.charAt(j) == 'X') j++;
                if (j == end.length()) return true;
                else return false;
            }
            else {
                while (i < start.length() && start.charAt(i) == 'X') i++;
                if (i == start.length()) return true;
                else return false;
            }
        }
        return true;
    }
}
