package Leetcode;

public class P0338_CountingBits {

    // approach 1:
    // make use of what you have produced already.
    // Divide the numbers in ranges like [0-1], [2-3], [4-7], [8-15] and so on.
    // Basically number of bits for each range of a power of 2 could be calculated from
    // number of bits of its nearest power of 2 and number of bits of their difference value

    // for example, 3 could be generated from its nearest power of 2 which is 2
    // and their difference(3 - 2 = 1) 1; 7 could be generated from its nearest
    // power of 2 which is 4 and their difference(7- 4 = 3) 3
    // 0    1    2    3    4    5    6    7    8    9
    //00   01   10   11  100  101  110  111 1000 1001


    public int[] countBits(int num) {
        if (num == 0) return new int[]{0};
        int[] dp = new int[num + 1];
        dp[1] = 1;
        int nearestPowerOfTwo = 2;
        for (int i = 2; i <= num; i++) {
            if (i > (nearestPowerOfTwo << 1) - 1) {
                nearestPowerOfTwo <<= 1;
            }
            if (i == nearestPowerOfTwo) {
                dp[i] = 1;
            }
            else {
                dp[i] = dp[i - nearestPowerOfTwo] + dp[nearestPowerOfTwo];
            }

        }
        return dp;
    }


    // approach 2
    // idea borrowed from leetcode discussion board
    // Basically, the trick is to realize the overlapping property of the problem
    // f[binary string without the last bit] + last bit is 1 or not

    // time: O(n)
    // space: O(n)

    public int[] countBits_2(int num) {
        int[] res = new int[num+1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }


}
