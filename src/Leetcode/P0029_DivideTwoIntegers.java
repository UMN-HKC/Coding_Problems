package Leetcode;

public class P0029_DivideTwoIntegers {

    // approach 1:

    // couldn't figure out initially
    // idea borrowed from discussion board
    // Basically, need to realize the integer representation in Java is Two's Complement
    // and the fact that the negative of Integer.MIN_VALUE is itself

    public int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;
        int quotient = 0, x = 0;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        while (a - b >= 0) {
            // '<< 1' in the for loop is to deal with
            for (x = 0; a - (b << x << 1) >= 0; x++);
            quotient += 1 << x;
            a -= b << x;
        }
        return (dividend > 0) == (divisor > 0) ? quotient : -quotient;
    }

    // approach 2: binary search

    public int divide_2(int dividend, int divisor) {
        if (divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
        boolean isNeg = (dividend > 0) ^ (divisor > 0);
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        int res = helper(ldividend, ldivisor);
        return (isNeg ? -res : res);
    }
    public int helper(long ldividend, long ldivisor) {
        if (ldividend == 0 || ldividend < ldivisor) return 0;
        long sum = ldivisor;
        int cnt = 1;
        while (sum + sum <= ldividend) {
            cnt += cnt;
            sum += sum;
        }
        return cnt + helper(ldividend - sum, ldivisor);
    }
}
