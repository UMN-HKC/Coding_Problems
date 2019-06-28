package Leetcode;
import java.util.*;

public class P0300_LongestIncreasingSubsequence {

    // couldn't figure out initially

    // approach 1: DP
    // time: O(n^2)
    // space: O(n)

    public int lengthOfLIS_dp(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int j = 1; j < dp.length; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                    max = Math.max(max, dp[j]);
                }
            }
        }
        return max;
    }


    // approach 2: binary search (patience sort)
    // this explanation is the best found in terms of this approach, thanks to Ethan Li.
    // link: https://segmentfault.com/a/1190000003819886

    // basically, we will keep an array called tails where tails[i] represents smallest tail
    // number of i+1 length increasing subsequence.
    // There are 3 situations when adding numbers to tail array
    // 1). nums[i] < tails[0], nums[i] is smaller than all tails of different length of subsequence,
    //      so we will need to update tails[0] which is subsequence of length 1.
    // 2). nums[i] > tails[len], we add nums[i] to tails[i+1]
    // 3). tails[0] < nums[i] < tails[len], binary search to find the first tail that's
    // greater than nums[i] and then update that tail

    // Thus, tail array will always be in ascending order
    // For example, when iterating numbers in [1,3,5,2,8,4,6] array, we will have tail acts as following:
    // [0,0,0,0,0,0, ...]
    //  1
    //  1 3
    //  1 3 5
    // when add the next number which is 2, it is not smaller than 1 or greater than 5, so we
    // need to binary search and update 3 to 2. Adding 8 fits our second situation. Again, binary
    // search for 4, 6, and update respective tails:
    //  1
    //  1 2
    //  1 3 6
    //  1 3 5 8

    // time: O(nlogn)
    // space: O(n)

    public int lengthOfLIS_bs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // len表示当前最长的升序序列长度（为了方便操作tails我们减1）
        int len = 0;
        int n = nums.length;
        // tails[i]表示长度为i的升序序列其末尾的数字
        int[] tails = new int[n];
        tails[0] = nums[0];
        // 根据三种情况更新不同升序序列的集合
        for (int i = 1; i < n; i++) {
            if (nums[i] < tails[0]) {
                tails[0] = nums[i];
            }
            else if (nums[i] > tails[len]) {
                tails[++len] = nums[i];
            }
            else {
                // 如果在中间，则二分搜索
                tails[binarySearch(tails, 0, len, nums[i])] = nums[i];
            }
        }
        return len + 1;
    }
    public int binarySearch(int[] tails, int l, int r, int target) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (tails[m] == target) {
                return m;
            }
            else if (tails[m] < target) {
                l = m + 1;
            }
            else {
                r = m - 1;
            }
        }
        return l;
    }
}
