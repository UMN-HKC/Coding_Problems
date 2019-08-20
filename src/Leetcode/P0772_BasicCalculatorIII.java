package Leetcode;

public class P0772_BasicCalculatorIII {

    // based on BasicCalculatorII, use recursion to deal with brackets

    public int calculate(String s) {
        s = "(" + s + ")";
        return helper(s, 0, s.length() - 1);
    }
    public int helper(String s, int l, int r) {
        l++;r--;
        int tempSum = 0, sum = 0, num = 0;
        char sign = '+';
        for (int i = l; i <= r; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            else if (c == '(') {
                int rp = findClosing(s, i);
                num = helper(s, i, rp);
                i = rp;
                if (i != r)
                    continue;
            }
            if ((!Character.isDigit(c) && c != ' ') || i == r) {
                if (sign == '+') {
                    sum += tempSum;
                    tempSum = num;
                }
                else if (sign == '-') {
                    sum += tempSum;
                    tempSum = -num;
                }
                else if (sign == '*') {
                    tempSum *= num;
                }
                else {
                    tempSum /= num;
                }
                sign = c;
                num = 0;
            }
        }
        return sum + tempSum;
    }
    public int findClosing(String s, int open) {
        int cnt = 0;
        for (int i = open; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            if (cnt == 0) {
                return i;
            }
        }
        return -1;
    }
}
