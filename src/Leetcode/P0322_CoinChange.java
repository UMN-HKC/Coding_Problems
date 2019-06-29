package Leetcode;
import java.util.*;

public class P0322_CoinChange {


    // initial approach: dp

    // time: O(amount * n)
    // space: O(amount)

    public static void main(String[] args) {
        P0322_CoinChange cc = new P0322_CoinChange();
        System.out.println(cc.coinChange_dfs(new int[]{1,5,2}, 11));

    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                int j = i - coin;
                if (j >= 0 && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);

                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // approach 2: greedy + dfs + pruning (fast, beats 99%)
    // great explanation from Huahua: https://www.youtube.com/watch?v=uUETHdijzkA
    // the idea is to try to use as many greater value coins as much and prune for cases when
    // result count is already smaller or equal to the current amount of coins

    // time: O(amount^n / (coin_1 * coin_2 * ... * coin_n))

    public int[] res = new int[]{Integer.MAX_VALUE};

    public int coinChange_dfs(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        if (res[0] == Integer.MAX_VALUE) return -1;
        return res[0];
    }
    public void dfs(int[] coins, int index, int amount, int count) {
        int coin = coins[index];
        // last element
        if (index == 0) {
            if (amount % coin == 0) {
                res[0] = Math.min(res[0], count + amount / coin);
            }
        }
        else {
            for (int k = amount / coin; k >= 0 && count + k < res[0]; k--) {
                dfs(coins, index - 1, amount - k * coin, count + k);
            }
        }
        return;
    }
}
