package Leetcode;

public class P0309_StockWithCooldown {

    // initially couldn't figure out
    // idea borrowed from discussion board
    // apply state machine thinking and DP
    // 参考state machine解法的讲解链接：
    // https://www.youtube.com/watch?v=oL6mRyTn56M

    // basically, we have 3 states: buy, sell, and rest
    // we will use 3 arrays to represent thew 3 states
    // hold[i]: the maximum profit we can make on ith day when the last operation is buy
    // sold[i]: the maximum profit we can make on ith day when the last operation is sold
    // rest[i]: the maximum profit we can make on ith day when the last operation is rest

    // time: O(n)
    // space: O(n)

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)   return 0;
        int[] hold = new int[prices.length + 1];
        int[] sold = new int[prices.length + 1];
        int[] rest = new int[prices.length + 1];
        hold[0] = -prices[0];

        for (int i = 1; i <= prices.length; i++) {
            // hold[i] can represent buy stock on ith day or have bought the
            // stock before i and not buy on this day (where hold[i - 1] means),
            // but still, the last operation is buy for hold[i]
            hold[i] = Math.max(rest[i-1] - prices[i-1], hold[i-1]);
            // we can only sell when the last operation is buying
            sold[i] = hold[i-1] + prices[i-1];
            rest[i] = Math.max(rest[i-1], sold[i-1]);
        }
        return Math.max(sold[prices.length], rest[prices.length]);
    }

    // space optimize: since state only relies on their previous states, we can use 3 state
    // variables to keep track of all the states

    // time: O(n)
    // space: O(1)
    public int maxProfit_space_optimize(int[] prices) {
        if (prices == null || prices.length == 0)   return 0;
        int sold = 0, rest = 0, hold = -prices[0];

        for (int i = 0; i < prices.length; i++) {
            int prevRest = rest;
            rest = Math.max(sold, rest);
            sold = hold + prices[i];
            hold = Math.max(hold, prevRest - prices[i]);
        }
        return Math.max(rest, sold);
    }
}
