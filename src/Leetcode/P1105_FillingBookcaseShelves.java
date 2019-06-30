package Leetcode;

public class P1105_FillingBookcaseShelves {

    // DP solution:
    // For each book, we first put it on a new level of the shelf which is the least preferable
    // way to do, in this case the current height will be dp[i - 1] + books[i- 1][1].
    // Then, we check back previously put books and see if it is possible to get better
    // arrangement(less height) by putting the current book together with the books at previous
    // level of the shelf. If better arrangement is possible, dp[i] will be updated.
    // The inner loop will terminate once accumulated width exceeds the bookshelf's width.

    // time: O(n^2)
    // space: O(n)

    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + books[i - 1][1];
            int height = books[i - 1][1];
            int width = books[i - 1][0];
            for (int j = i - 1; j > 0 && width + books[j - 1][0] <= shelf_width; j--) {
                height = Math.max(height, books[j - 1][1]);
                width += books[j - 1][0];
                dp[i] = Math.min(dp[i], height + dp[j - 1]);
            }
        }
        return dp[n];
    }
}
