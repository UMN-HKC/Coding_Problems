package Leetcode;

public class P0309_StockWithCooldown {

    // approach 1: DP

    // hold[i]: the maximum profit if we are holding stock on ith day
    // unhold[i]: the maximum profit if we are not holding stock on ith day

    // hold[i] comes from maximum between:
    //  1. buy on ith day: unhold[i - 2] - prices[i]
    //  2. no transaction on ith day: hold[i - 1]
    // unhold[i] comes from maximum between:
    //  1. sell on ith day: hold[i - 1] + prices[i]
    //  2. no selling on ith day: unhold[i - 1]

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int n = prices.length;
        int[] hold = new int[n];
        int[] unhold = new int[n];
        hold[0] = -prices[0];
        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], -prices[i] + (i > 1 ? unhold[i - 2] : 0));
            unhold[i] = Math.max(unhold[i - 1], hold[i - 1] + prices[i]);
        }
        return unhold[n - 1];
    }
}
