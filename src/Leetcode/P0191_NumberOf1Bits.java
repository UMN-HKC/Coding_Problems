package Leetcode;

public class P0191_NumberOf1Bits {

    public int hammingWeight(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum += n&1;
            n >>= 1;
        }
        return sum;
    }
}
