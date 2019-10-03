package Leetcode;

public class P1105_FillingBookstoreShelves {

    // approach 1: DP

    // The basic idea is to apply dynamic programming since the result of placing ith book
    // will reply on the result of placing all its previous books.

    // dp[i] represents the minimum height after we place all i books.
    // when placing ith book, we have two choices: either place it on the top of the last level
    // or we can look back and see if we can put ith book with previous books to make the
    // height even shorter

    // dp[j] => min(dp[j] + max(height[j+1] .. height[i])), where sum(width[j+1] + ... + sum(width[i]) <= shelve_width

    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int[] book = books[i];
            int width = book[0];
            int height = book[1];
            dp[i + 1] = dp[i] + height;

            int j = i - 1;
            while (j >= 0 && width + books[j][0] <= shelf_width) {
                height = Math.max(height, books[j][1]);
                dp[i + 1] = Math.min(height + dp[j], dp[i + 1]);
                width += books[j][0];
                j--;
            }
        }
        return dp[n];
    }
}
