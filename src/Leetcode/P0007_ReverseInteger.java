package Leetcode;

public class P0007_ReverseInteger {

    // initial approach: use long to detect integer overflow when reversing
    // if only allowed to deal with integer, then proceed to approach 2,
    // basically add a check within the while loop

    public int reverse_initial(int x) {
        long res = 0;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int) res;
    }

    public int reverse_only_integer(int x) {
        int res = 0;
        while (x != 0) {
            int prev = res;
            res *= 10;
            res += x % 10;
            if (res / 10 != prev) return 0;
            x /= 10;
        }
        return res;
    }
}
