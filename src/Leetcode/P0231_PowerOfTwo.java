package Leetcode;

public class P0231_PowerOfTwo {

    // approach 1: iterative

    public boolean isPowerOfTwo(int n) {
        if (n == 0) return false;
        int cnt = 0;
        while (n != 0) {
            if ((n & 1) == 1) cnt++;
            if (cnt > 1) return false;
            n >>= 1;
        }
        return true;
    }

    // approach 2: 1-liner

    // power of two means only one 1 in its binary bit representation,
    // so (n & (-n)) gets us the rightmost 1 bit and if this equals n
    // which means n only has this 1 bit, then n is a power of two

    public boolean isPowerOfTwo_2(int n) {
        return n > 0 && (n & (-n)) == n;
    }

    // approach 3: remove right-most bit
    public boolean isPowerOfTwo_3(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
}
