package Leetcode;

public class P0441_ArrangingCoins {

    // approach 1: O(n)

    public int arrangeCoins(int n) {
        int level = 0;
        while (n >= level + 1) {
            level++;
            n -= level;
        }
        return level;
    }


    // approach 2: binary search

    public int arrangeCoins_2(int n) {
        long l = 1, r = n;
        while (l <= r) {

            long m = l + (r - l) / 2;

            long cnt = (1 + m) * m / 2;
            if (cnt == n) {
                return (int)m;
            }
            else if (cnt < n) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return (int)r;

    }
}
