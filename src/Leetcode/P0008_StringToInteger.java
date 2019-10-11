package Leetcode;

public class P0008_StringToInteger {

    /***
     initial approach: brute force 实现题
     steps:
        - skip initial white space
        - check sign
        - loop while the next one is digit
         - return sign * num
     ***/

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        int i = 0, num = 0, sign = 1, n = str.length();
        // skip white spaces
        while (i < n && str.charAt(i) == ' ') i++;
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = str.charAt(i) == '+' ? 1 : -1;
            i++;
        }

        while (i < n && Character.isDigit(str.charAt(i))) {
            int digit = str.charAt(i) - '0';
            if ((Integer.MAX_VALUE - digit) / 10 < num || (Integer.MIN_VALUE + digit) / 10 > num ||
                    (Integer.MAX_VALUE / 10 == num && sign == -1 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + digit;
            i++;
        }
        return num * sign;
    }
}
