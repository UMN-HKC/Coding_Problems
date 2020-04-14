package Leetcode;

public class P1411_NumberofWaystoPaintNTimesThreeGrid {


    // approach 1: DP
    // https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/discuss/574943/Java-Detailed-Explanation-with-Graph-Demo-DP-Easy-Understand

    public int numOfWays(int n) {
        int modulo = (int)Math.pow(10, 9) + 7;
        long twoColorCombination = 6;
        long threeColorCombination = 6;

        for (int i = 2; i <= n; i++) {
            long temp = twoColorCombination;
            twoColorCombination = (2 * threeColorCombination + 3 * temp) % modulo;
            threeColorCombination = (2 * threeColorCombination + 2 * temp) % modulo;
        }
        return (int)(twoColorCombination + threeColorCombination) % modulo;
    }
}
