package Leetcode;

public class P0171_ExcelSheetColumnNumber {

    // initial approach, starting from left to right(right to left is also fine)
    // just accumulating result by adding "power of 26" times current character respective to 'A'

    public int titleToNumber(String s) {
        int pow = s.length() - 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            res += (c + 1 - 'A') * Math.pow(26, pow - i);
        }
        return res;
    }
}
