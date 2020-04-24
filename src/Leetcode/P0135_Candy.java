package Leetcode;

import java.util.Arrays;

public class P0135_Candy {

    // approach 1: greedy

    // initialize the candies array with all 1's and then scan from left to right
    // and then scan from right to left to update candies according to ratings array
    // Note that when we scan from left to right, the only thing we want to make sure is
    // that the current candy should be valid with the previous one; and when we scan from
    // right to left, the only thing we want to make sure is that the current candy should
    // be valid with the next one.

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1] && candies[i] <= candies[i - 1]) {
                // check if current should get one more
                // candy than the previous child
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                // check if the current should get more candy
                // than the next child
                candies[i] = candies[i + 1] + 1;
            }
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += candies[i];
        }
        return total;
    }
}
