package Leetcode;

public class P0029_DivideTwoIntegers {

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
}
