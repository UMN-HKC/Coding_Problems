package Leetcode;

public class P0209_MinimumSizeSubarraySum {

    // approach 1: sliding window  (best)

    // time: O(n)
    // space: O(1)

    public int minSubArrayLen_2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int runningSum = 0;
        int l = 0, r = 0, min = Integer.MAX_VALUE;
        while (r < nums.length) {
            // try to expand and have sum equaling to s first
            // try to make it valid
            if (runningSum < s) {
                runningSum += nums[r++];
            }
            // while valid, update result and make it invalid again
            // so we could explore other possible better answers
            while (l < r && runningSum >= s) {
                // update result
                min = Math.min(min, r - l);
                runningSum -= nums[l++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // two binary search approaches:

    // approach 2.1:

    // Basically we do binary search on the window size. Say, array length is k.
    // We first see if window size of k/2 can contain a sum equaling s by moving
    // window through the entire array. If it contains, we shrink our window and
    // see if we could find even smaller window that contains s. Otherwise, we
    // expand the window. It mimic the binary search's 'mid-1' and 'mid +1' by
    // shrinking or expanding the window.

    // time: O(nlogn)
    // space: O(1)

    public int minSubArrayLen_3(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = 0, start = 1, end = nums.length;
        while (start <= end) {
            int winSize = start + (end - start) / 2;
            if (contains(winSize, nums, s)) {
                end = winSize - 1;
                min = winSize;
            }
            else {
                start = winSize + 1;
            }
        }
        return min;
    }
    public boolean contains(int winSize, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= winSize) sum -= nums[i - winSize];
            sum += nums[i];
            if (sum >= s) return true;
        }
        return false;
    }

    // approach 2.2: binary search

    // The basic idea is that we will build a prefix sum array and since the prefix sum array
    // is ordered, we can iterate each lower bound value and apply binary search to find
    // its upper bound that satisfies our condition.

    // time: O(nlogn)
    // space: O(n)

    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] runningSum = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            runningSum[i + 1] = runningSum[i] + nums[i];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < runningSum.length - 1; i++) {
            // for each iteration, we try to find the leftmost element
            // to the right of number at index i that the cumulative
            // sum between them is greater or equal to s
            int l = i + 1, r = runningSum.length - 1;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (runningSum[m] - runningSum[i] >= s) {
                    r = m;
                }
                else {
                    l = m + 1;
                }
            }
            if (runningSum[r] - runningSum[i] >= s) min = Math.min(min, r - i);

        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
