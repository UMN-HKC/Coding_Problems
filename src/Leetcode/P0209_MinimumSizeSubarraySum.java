package Leetcode;

public class P0209_MinimumSizeSubarraySum {

    // approach 1:

    // time: O(n)
    // space: O(n)

    public int minSubArrayLen_1(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] runningSum = new int[nums.length + 1];
        for (int i = 1; i < runningSum.length; i++) {
            runningSum[i] = runningSum[i - 1] + nums[i - 1];
        }
        int l = 0, r = 1, min = Integer.MAX_VALUE;
        while (r < runningSum.length) {
            // try to expand and have sum equaling to s first
            // make it valid
            if (runningSum[r] - runningSum[l] < s) {
                r++;
            }
            else {
                // while it is valid, update result and make it invalid again
                while (l < r && runningSum[r] - runningSum[l] >= s) {
                    // update result
                    min = Math.min(min, r - l);
                    // shrink
                    l++;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    // approach 2:

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

    // approach 3: for follow-up asking O(nlogn) solution, interesting approach

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
}
