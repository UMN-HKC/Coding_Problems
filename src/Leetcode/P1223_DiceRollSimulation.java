package Leetcode;

public class P1223_DiceRollSimulation {

    // approach 1: DP
    // link: https://leetcode.com/problems/dice-roll-simulation/discuss/403756/Java-Share-my-DP-solution
    // dp[i][j]: number of combinations by rolling i dices with the last number being j
    // dp[i].length will be 7, with the last column storing the sum for dp[i]

    // Let's take an example:
    // Assuming restriction for 1 is 2 times and we meet the case axxb (a, b are indexes of uncertain values).
    // We all know we can not take 1 at index-b when xx = '11', these are all invaild so we need to remove them.
    //
    // First of all, the total possible cases of dp[b][1] are sum of dp[b-1][1~6]
    // for index-b if we want to choose 1 so we need to consider the case that the two consecutive number
    // before b is '11'. Also we need to be careful about value at the index-a. It shouldn't be '1'.
    // In short we need to remove all possible cases of a11 and a is not 1.
    // The transition equation = dp[b][1] = sum(dp[b-1][1~6]) - sum(dp[a][2~6(except 1)])

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
