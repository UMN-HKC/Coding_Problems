package Leetcode;

import java.util.Arrays;

public class P0135_Candy {

    // approach 1: greedy
    // initialize the candies array with all 1's and then scan from left to right
    // and then scan from right to left to update candies according to ratings array
    // Note that when we scan from right to left, we want to keep the maximum between
    // candies[i] and candies[i + 1] + 1

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int m = ratings.length;
        int[] candies = new int[m];
        Arrays.fill(candies, 1);
        for (int i = 1; i < m; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        int total = 0;
        for (int num : candies) total += num;
        return total;
    }
}
