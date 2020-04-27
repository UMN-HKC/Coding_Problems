package Leetcode;
import java.util.*;

public class P1425_ConstrainedSubsetSum {

    // approach 1: dp + sliding window
    // dp[i]: the maximum subset sum when the last element chosen is at i position
    // use heap to optimize dp

    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int max = nums[0];
        Deque<Integer> dq = new ArrayDeque<>();
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            // remove indices outside window from front
            while (!dq.isEmpty() && i - dq.peekFirst() > k) dq.pollFirst();
            dp[i + 1] = nums[i] + (dq.isEmpty() ? 0 : dp[dq.peekFirst() + 1]);
            max = Math.max(max, dp[i + 1]);
            // remove from behind if they are smaller than
            // the current one we will be adding
            while (!dq.isEmpty() && (dp[dq.peekLast() + 1] <= dp[i + 1])) dq.pollLast();
            if (dp[i + 1] > 0)
                dq.offerLast(i);
        }
        return max;
    }
}
