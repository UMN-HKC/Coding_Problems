package Leetcode;

public class P0162_FindPeakElement {

    // initial approach: binary search

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0;
        if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            // check left edge
            if (mid == l && nums[mid] > nums[mid + 1]) {
                return l;
            }
            //check right edge
            else if (mid == r && nums[mid] > nums[mid - 1]) {
                return r;
            }
            // return if found
            else if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            }
            // go to larger side since there will always be a peak in the larger side
            else if (nums[mid] < nums[mid + 1]) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return l;
    }
}
