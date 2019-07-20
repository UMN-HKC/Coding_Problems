package Leetcode;

public class P0276_PaintFence {


    // approach 1: DP

    // Brute force DP approach with auxiliary space(will optimize later):
    // same[i] : # of ways to paint the current post with the same color as (i - 1)th post
    // diff[i]: # of ways to paint the current post with a different color as (i - 1)th post

//    To get same[i], we cannot derive it from same[i-1] because same[i-1] represents same color
//    at (i-2)th post and (i-1)th post. Instead, same[i] should be same as diff[i-1],
//    because diff[i-1] represents different color at (i-2)th post and (i-1)th post.
//
//    To get diff[i], we must use a different color which directly sets our current option
//    to (k - 1), and the total number of ways will be (k - 1) * (number of ways to
//    paint (i-1)th post same color and different color)

    public int numWays_1(int n, int k) {
        if (n == 0 || k == 0) return 0;
        int[] same = new int[n];
        int[] diff = new int[n];
        // note that same[0] should be initialized to 0 because this is the
        // first post and according to our recurrence, same[0] = diff[-1] which
        // is out of bounds so it should be 0 initially.
        diff[0] = k;

        for (int i = 1; i < n; i++) {
            same[i] = diff[i - 1];
            diff[i] = (same[i - 1] + diff[i - 1]) * (k - 1);
        }
        return same[n-1] + diff[n-1];
    }

    // optimize space to O(1)

    public int numWays_2(int n, int k) {
        if (n == 0 || k == 0) return 0;
        int same = 0, diff = k;

        for (int i = 1; i < n; i++) {
            int temp = same;
            same = diff;
            diff = (temp + diff) * (k - 1);
        }
        return same + diff;
    }

}
