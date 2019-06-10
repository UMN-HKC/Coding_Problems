package Leetcode;

public class P0121_Stock {

    // initial approach:
    // keep index of the min number and max profit we could make if sold today
    // the problem with this approach is that we assume we could buy and sell within the same
    // day, and we also reply on the price of a particular day in the array

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int min = 0;
        for (int i = 0; i < prices.length; i++) {
            max = Math.max(prices[i] - prices[min], max);
            min = prices[i] < prices[min] ? i : min;
        }
        return max;
    }

    // approach 2: kadane's algorithm

    // Simple idea of the Kadane’s algorithm is to look for all positive contiguous segments of the
    // array (max_ending_here is used for this). And keep track of maximum sum contiguous segment
    // among all positive segments (max_so_far is used for this). Each time we get a positive sum
    // compare it with max_so_far and update max_so_far if it is greater than max_so_far

    public int maxProfit_kandane(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int curMax = 0;
        int maxSofar = 0;
        for (int i = 1; i < prices.length; i++) {
            curMax += prices[i] - prices[i - 1];
            curMax = Math.max(0, curMax);
            maxSofar = Math.max(maxSofar, curMax);
        }
        return maxSofar;
    }
}
