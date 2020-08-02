package Leetcode;

public class P0461_HammingDistance {

    // approach 1

    public int hammingDistance(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            cnt += (x & 1) ^ (y & 1);
            x = x >> 1;
            y = y >> 1;
        }
        return cnt;
    }
}
