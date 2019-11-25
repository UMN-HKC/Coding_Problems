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
    // space: O(n)

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

    public int minSubArrayLen(int s, int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int end = helper(prefixSum, prefixSum[i] + s, i + 1, prefixSum.length - 1);
            if (end == prefixSum.length) break;
            min = Math.min(min, end - i);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    private int helper(int[] prefixSum, int target, int s, int e) {
        while (s < e) {
            int mid = s + (e - s) / 2;
            if (prefixSum[mid] < target) {
                s = mid + 1;
            }
            else {
                e = mid;
            }
        }
        return prefixSum[s] >= target ? s : s + 1;
    }
}
