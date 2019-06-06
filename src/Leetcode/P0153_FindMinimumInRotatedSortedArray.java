package Leetcode;

public class P0153_FindMinimumInRotatedSortedArray {

    // time: O(logn)
    // space: O(1)

    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return nums[start];
    }
}
