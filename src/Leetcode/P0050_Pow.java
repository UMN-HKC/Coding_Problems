package Leetcode;

public class P0050_Pow {

    // kind of suck at these math questions currently, was trying to come up
    // with an iterative solution with bit manipulation but failed to implement it out

    // approach 1: recursive approach (idea borrowed from discussion board)

    public double myPow(double x, int n) {
        if (n == 0) return 1;
        // deals with n being Integer.MIN_VALUE
        if (n == Integer.MIN_VALUE) {
            return 1 / x * myPow(x, n + 1);
        }
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : (x * myPow(x * x, n / 2));
    }


    // iterative approach: bit manipulation
    // for example,

    /***
     for example,

     x = 2, n = 9
     n in terms of binary rep: 1001
     2^9 = 2^3 + 2^0

     => so basically to get 2^n is to get the accumulated power of x to the ith bit where
     the bit is 1. Note that in each iteration, we will right shift n and power x. But we only
     add to res when we encounter bit which is 1.
     */
    public double myPow_iterative(double x, int n) {
        double res = 1;
        // set absN to deal with 'n == Integer.MIN_VALUE'
        long absN = Math.abs((long)n);
        while (absN > 0) {
            if ((absN & 1) == 1) {
                res *= x;
            }
            absN >>= 1;
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }
}
