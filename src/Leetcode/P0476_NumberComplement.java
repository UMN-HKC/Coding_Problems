package Leetcode;

public class P0476_NumberComplement {

    // approach 1: bit manipulation
    public int findComplement(int num) {
        int i = 1 << 30;
        while ((i & num) == 0) i >>= 1;
        while (i != 0) {
            num = num ^ i;
            i >>= 1;
        }
        return num;
    }

    // approach 2: bit manipulation, one pass
    public int findComplement_2(int num) {
        int i = ~0;
        while ((i & num) != 0) i <<= 1;
        return (~num) & (~i);
    }
}
