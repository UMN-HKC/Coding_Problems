package Leetcode;

public class P1060_MissingElementInSortedArray {

    // approach 1: binary search

    // The basic idea is that since the array is sorted, we can utilize the sorted property
    // along with k value to apply binary search. First of all, if the total missing number
    // is less than k, simply return the end number plus (k - missingWithinRange). Otherwise,
    // we binary search by pick a middle index and calculate the number of missing numbers
    // on the left part, if that number is smaller than k which means the result will be from
    // [m, r], otherwise the result will be from [m, r]. We also update k.

    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int missingWithinRange = nums[len - 1] - nums[0] + 1 - len;
        if (missingWithinRange < k) return nums[len - 1] + k - missingWithinRange;
        int l = 0, r = len - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            int leftMissing = (nums[m] - nums[l]) - (m - l);
            if (leftMissing < k) {
                k -= leftMissing;
                l = m;
            }
            else {
                r = m;
            }
        }
        return nums[l] + k;
    }
}
