package Leetcode;

public class P0122_StockII {

    // couldn't figure out initially
    // Idea borrowed from discussion board
    // The key idea is to identify that the problem is actually to find sum of
    // all increasing sub-arrays. Note that we can not buy and sell a stock within one day
    // so the following code follows the logic of the problem
    // however, the code could be simplified to the second version though the logic might be violated

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int prev = 0;
        int res = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] <= prices[i + 1]) continue;
            else {
                res += prices[i] - prices[prev];
                prev = i+1;
            }
        }
        return res + prices[prices.length - 1] - prices[prev];
    }

    public int maxProfit_simplified(int[] prices) {
        int total = 0;
        for (int i=0; i< prices.length-1; i++) {
            if (prices[i+1]>prices[i]) total += prices[i+1]-prices[i];
        }

        return total;
    }
}
