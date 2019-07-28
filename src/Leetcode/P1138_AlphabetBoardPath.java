package Leetcode;

public class P1138_AlphabetBoardPath {

    // approach 1: The key here is to realize we do not need to construct a
    // graph or grid or something. Because of the contiguous characteristic of letters
    // from 'a' to 'z', we can directly move our r and c in terms of its relative
    // position to the letter we are looking for.

    // time: O(n)
    // space: O(n)

    public String alphabetBoardPath(String target) {
        int r = 0, c = 0;
        StringBuilder sb = new StringBuilder();
        for (char ch : target.toCharArray()) {
            int y = (ch - 'a') / 5;
            int x = (ch - 'a') % 5;
            while (r != y || c != x) {
                if (r == 5 && c == 0) {
                    sb.append("U");
                    r--;
                }
                else if (c < x) {
                    sb.append("R");
                    c++;
                }
                else if (c > x) {
                    sb.append("L");
                    c--;
                }
                else if (r < y) {
                    sb.append("D");
                    r++;
                }
                else if (r > y) {
                    sb.append("U");
                    r--;
                }
            }
            sb.append("!");
        }
        return sb.toString();
    }
}
