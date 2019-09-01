package Leetcode;

public class P0263_UglyNumber {

    // approach 1:

    // The basic idea is to divide the number by 2 or 3 or 5 if divisible until
    // the number gets to 1. If before getting to 1, it is not divisible by 2 or
    // 3 or 5, then it is not an ugly number.

    public boolean isUgly(int num) {
        if (num < 1) return false;
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
            }
            else if (num % 3 == 0) {
                num /= 3;
            }
            else if (num % 5 == 0) {
                num /= 5;
            }
            else {
                return false;
            }
        }
        return true;
    }
}
