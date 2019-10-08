package Leetcode;

public class P0007_ReverseInteger {

    // approach 1
    // use long to detect integer overflow when reversing
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

    // approach 2:
    // The idea is that if overflow happens, the current overflow number when calculated
    // backwards will not equal to its previous number(which we need to store beforehand)

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

    // approach 3:

    public int reverse(int x) {
        if (x == 0) return x;
        int num = 0;
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            if (num > 0 && (Integer.MAX_VALUE - digit) / 10 < num) {
                return 0;
            }
            if (num < 0 && (Integer.MIN_VALUE - digit) / 10 > num) {
                return 0;
            }
            num = num * 10 + digit;
        }
        return num;
    }


}
