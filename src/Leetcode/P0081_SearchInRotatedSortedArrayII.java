package Leetcode;

public class P0081_SearchInRotatedSortedArrayII {

    // idea borrowed from "basketwang"
    // https://www.youtube.com/watch?v=eG27FBcmy1k
    // similar to the other one, but with worst case "0,0,0,3,-1,0,0" we need to do some extra work
    // since nums[mid] == nums[start], the initial condition gets messed up, so we need to shift
    // start until nums[start] != nums[mid]

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[start]) {
                start++;
            }
            else if (nums[mid] > nums[start]) {
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
        return nums[start] == target ? true : false;
    }
}
