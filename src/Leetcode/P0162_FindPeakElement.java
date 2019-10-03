package Leetcode;

public class P0162_FindPeakElement {

    // approach 1: binary search

    // The basic idea is that since we are peak element must exist and contiguous elements
    // are different, we can simply compare nums[m] and nums[m + 1], and there will only
    // be 2 situations: nums[m] > nums[m + 1], move r to m; otherwise, move l to m + 1

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < nums[m + 1]) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return l;
    }
}
