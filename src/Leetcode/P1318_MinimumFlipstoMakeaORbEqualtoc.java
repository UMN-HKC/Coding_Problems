package Leetcode;

public class P1318_MinimumFlipstoMakeaORbEqualtoc {

    // approach 1: bit manipulation

    public int minFlips(int a, int b, int c) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            int candA = a & 1;
            int candB = b & 1;
            int target = c & 1;
            a = a >> 1;
            b = b >> 1;
            c = c >> 1;
            int or = candA | candB;
            if (or != target) {
                if (target == 1) cnt += 1;
                else cnt += candA == 1 && candB == 1 ? 2 : 1;
            }
        }
        return cnt;
    }
}
