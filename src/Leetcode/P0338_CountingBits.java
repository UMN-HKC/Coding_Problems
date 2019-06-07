package Leetcode;

public class P0338_CountingBits {

    // couldn't figure out initially
    // idea borrowed from leetcode discussion board
    // Basically, the trick is to realize the overlapping property of the problem
    // f[binary string without the last bit] + last bit is 1 or not

    // time: O(n)
    // space: O(n)

    public int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }
}
