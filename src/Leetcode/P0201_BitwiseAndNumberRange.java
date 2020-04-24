package Leetcode;

public class P0201_BitwiseAndNumberRange {

    // from discussion board
    // the idea is to shift both lower and higher bound numbers to the right until
    // they are equal, because all the right hand side bits we discarded will be
    // eliminated when we do bitwise & iteratively
    // 1. last bit of (odd number & even number) is 0.
    // 2. when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
    // 3. Move m and n rigth a position.

    // time: O(log(32))
    // space: O(1)

    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            i++;
        }
        n = n << i;
        return n;
    }
}
