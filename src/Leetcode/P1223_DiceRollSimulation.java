package Leetcode;

public class P1223_DiceRollSimulation {

    // approach 1: DP
    // link: https://leetcode.com/problems/dice-roll-simulation/discuss/403756/Java-Share-my-DP-solution
    // dp[i][j]: number of combinations by rolling i dices with the last number being j
    // dp[i].length will be 7, with the last column storing the sum for dp[i]
    // note that when doing subtraction, we need to add modulo after subtraction
    // Example: (6 - 5) % 3 = 1, but (6%3 - 5%3) = -2 we need to add 3 to make it as positive value.


    public static long modulo = (long)Math.pow(10, 9) + 7;
    public int dieSimulator(int n, int[] rollMax) {
        long[][] dp = new long[n + 1][7];
        for (int i = 0; i < 6; i++) {
            dp[1][i] = 1;
        }
        dp[1][6] = 6;
        for (int i = 2; i <= n; i++) {
            long sum = 0;
            for (int num = 0; num < 6; num++) {
                dp[i][num] = dp[i - 1][6];
                if (i - rollMax[num] == 1) {
                    dp[i][num] = (dp[i][num] - 1) % modulo;
                }
                if (i - rollMax[num] > 1) {
                    dp[i][num] = ((dp[i][num] - (dp[i - rollMax[num] - 1][6] - dp[i - rollMax[num] - 1][num])) + modulo) % modulo;
                }
                sum = (sum + dp[i][num]) % modulo;
            }
            dp[i][6] = sum;
        }
        return (int)dp[n][6];
    }
}
