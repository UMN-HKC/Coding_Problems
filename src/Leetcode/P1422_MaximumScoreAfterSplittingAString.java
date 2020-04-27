package Leetcode;

public class P1422_MaximumScoreAfterSplittingAString {

    // approach 1: brute force

    public int maxScore(String s) {
        if (s == null || s.length() == 0) return 0;
        int ones = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') ones++;
        }
        int zeros = s.charAt(0) == '0' ? 1 : 0;
        ones -= s.charAt(0) == '1' ? 1 : 0;
        int res = zeros + ones;

        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            }
            else {
                ones--;
            }
            res = Math.max(res, ones + zeros);
        }
        return res;
    }
}
