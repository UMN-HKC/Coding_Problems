package Leetcode;

public class P0248_StrobogrammaticNumberIII {

    // couldn't figure out initially, idea borrowed from discussion board

    // the basic idea is:
    // - Construct char arrays from low.length() to high.length()
    // - Add stro pairs from outside
    // - When left > right, add eligible count

    // note that use char[][] instead of Character char will improve time complexity
    char[][] pairs ={{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};

    public int strobogrammaticInRange(String low, String high) {

        int[] count = new int[]{0};
        // for each length that's greater or equal to low string's length but less or equal to
        // high string's length, we explore their possible combinations
        for (int len = low.length(); len <= high.length(); len++) {
            char[] c = new char[len];
            dfs(low, high, c, 0, len - 1, count);
        }
        return count[0];
    }

    public void dfs(String low, String high, char[] chars, int left, int right, int[] count) {
        // when left > right, it means we have filled up our char array from outside
        if (left > right) {
            String s = new String(chars);
            System.out.println(s);
            // check when same length, cause all length between low's and high's length are what we want
            if ((s.length() == low.length() && s.compareTo(low) < 0) || (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count[0]++;
            return;
        }

        for (char[] pair : pairs) {
            chars[left] = pair[0];
            chars[right] = pair[1];
            // '0' at front is not valid except for '0' itself
            if (chars[0] == '0' && chars.length != 1 ) {
                continue;
            }
            // when length is odd, left and right could overlap, in this case we check if
            // the middle char is a valid stro({'0', '0'}, {'1', '1'} and {'8', '8'} are valid)
            if (left == right && pair[0] != pair[1]) {
                continue;
            }
            dfs(low, high, chars, left + 1, right - 1, count);
        }
        return ;
    }
}
