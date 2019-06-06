package Leetcode;

public class P0033_SearchInRotatedSortedArray {

    // binary search exclusive condition
    // we haven't checked the position when start == end, so we do a final check
    // outside the while loop

    public int search_(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                }
                else {
                    start = mid + 1;
                }
            }
            else {
                if (target < nums[start] && target > nums[mid]) {
                    start = mid + 1;
                }
                else {
                    end = mid;
                }
            }
        }
        return nums[start] == target ? start : -1;
    }

    // binary search inclusive condition
    // since we already checked all possible indices, so return -1 in the end

    public int search_inclusive(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
            else {
                if (target < nums[start] && target > nums[mid]) {
                    start = mid + 1;
                }
                else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

}
