package Leetcode;

public class P0035_SearchInsertPosition {

    // initial approach: binary search with inclusive condition
    // this covers cases when target is at position 0 and beyond the end of index

    public int searchInsert_inclusive(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return start;
    }

    // exclusive approach
    // this does not guarantee case when target is beyond the end of index,
    // but a smart trick here is to set end = nums.length to handle that case
    // will we get ArrayIndexOutOfBoundsException? No, because '/' always returns
    // the lower bound value of (a+b)/2

    public int searchInsert_exclusive(int[] nums, int target) {
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        return start;
    }
}
