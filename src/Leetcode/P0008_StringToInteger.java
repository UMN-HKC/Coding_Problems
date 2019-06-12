package Leetcode;

public class P0008_StringToInteger {

    /***
     initial approach: brute force 实现题
     steps:
        - remove white space
        - check sign at index 0
        - loop
            - if digit:
                - include
                - check if overflow
                    - if overflow: return
                    - continue
            - else: return
         - return
     ***/

    public int myAtoi(String str) {
        // remove white space
        str = str.trim();
        if (str.length() == 0 || (str.charAt(0) != '+' && str.charAt(0) != '-' && (str.charAt(0) < '0' || str.charAt(0) > '9'))) {
            return 0;
        }
        int res = 0;
        int itr = 0;
        int sign = 1;
        if (str.charAt(0) == '+') {
            itr++;
        }
        else if (str.charAt(0) == '-') {
            itr++;
            sign = -1;
        }

        // use prev to detect overflow
        int prev = res;
        while (itr < str.length()) {
            char c = str.charAt(itr);
            if (c >= '0' && c <= '9') {
                res *= 10;
                res += (c - '0');
                // if overflow detected, return
                if (res / 10 != prev) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                prev = res;
            }
            else {
                // if not a digit at current index, return result
                return res * sign;
            }
            itr++;
        }
        return sign * res;
    }
}
