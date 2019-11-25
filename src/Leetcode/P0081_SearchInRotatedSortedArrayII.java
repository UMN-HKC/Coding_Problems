package Leetcode;

public class P0081_SearchInRotatedSortedArrayII {

    // approach 1: binary search

    // The basic idea is that we can still apply the approach we used in solving
    // the first problem, but in here we cannot just set l to m + 1 or r to m - 1
    // because there are duplicate values in the array and the target might be within
    // duplicate values and might be skipped if we do not do some extra check.
    // Thus, after checking if nums[mid] is equal to target, we start shrinking our
    // search window by checking either nums[mid] is equal to nums[l] or nums[mid]
    // is equal to nums[r]. Because since we already know nums[mid] is not target,
    // and we want to reduce the search space but we cannot directly cut the search
    // space, the best we can do is to cut what I call "junk space" which in this
    // case is the value on the boundary that's same as nums[mid], which has been
    // checked that is not the answer. By doing this, we will shrink the size but
    // won't skip the real answer. Unfortunately doing this will cause O(n) in worst case

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] == nums[l]) {
                l++;
            }
            else if (nums[mid] >= nums[l]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
            else {
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                }
                else {
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
