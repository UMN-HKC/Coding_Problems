package Leetcode;

public class P0190_ReverseBits {

    // initially couldn't figure out
    // idea borrowed from discussion board
    // basically, for each bit in n, we add to our res which is initially 0, and shift
    // result to left 1 bit and n to right 1 bit. Be careful that when i == 31, we do not
    // shift result anymore since that will eliminate MSB

    // time: O(n)
    // space: O(1)

    public int reverseBits_initial(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            // the tricky part here is to realize that we could use addition to get LSB
            res += 1 & n;
            n = n >>> 1;
            if (i < 31) {
                res = res << 1;
            }
        }
        return res;
    }

    // approach 2: smart approach O(1) time

    //    for 8 bit binary number abcdefgh, the process is as follow:
    //    abcdefgh -> efghabcd -> ghefcdab -> hgfedcba

    public int reverseBits_smart(int n) {
        int ret=n;
        ret = ret >>> 16 | ret<<16;
        ret = (ret & 0xff00ff00) >>> 8 | (ret & 0x00ff00ff) << 8;
        ret = (ret & 0xf0f0f0f0) >>> 4 | (ret & 0x0f0f0f0f) << 4;
        ret = (ret & 0xcccccccc) >>> 2 | (ret & 0x33333333) << 2;
        ret = (ret & 0xaaaaaaaa) >>> 1 | (ret & 0x55555555) << 1;
        return ret;
    }
}
