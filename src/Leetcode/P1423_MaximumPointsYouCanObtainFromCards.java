package Leetcode;

public class P1423_MaximumPointsYouCanObtainFromCards {

    // approach 1: sliding window sort of approach
    // The idea is to keep a window of size k. We are only interested in the first k
    // elements and the last k elements.

    public int maxScore(int[] cardPoints, int k) {
        int res = 0;
        int windowSum = 0;
        int n = cardPoints.length;
        for (int i = 0; i < 2 * k; i++) {
            int frontIdx = n - k + i;
            windowSum += cardPoints[frontIdx % n];
            if (i >= k) {
                windowSum -= cardPoints[(frontIdx - k) % n];
            }
            if (i >= k - 1) {
                res = Math.max(res, windowSum);
            }
        }
        return res;
    }
}
