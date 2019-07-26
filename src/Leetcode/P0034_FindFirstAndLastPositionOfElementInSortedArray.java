package Leetcode;

public class P0034_FindFirstAndLastPositionOfElementInSortedArray {

    // initial approach
    // pass a flag to indicate whether we want first or last target element

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        res[0] = find(nums, target, 0, nums.length - 1, 0);
        res[1] = find(nums, target, 0, nums.length - 1, 1);
        return res;
    }
    public int find(int[] nums, int target, int l, int r, int flag) {
        // l <= r because we want to check each element
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (flag == 0) {
                // l is always valid since we only move l when target is not found
                if (nums[m] >= target) {
                    r = m - 1;
                }
                else {
                    l = m + 1;
                }
            }
            else {
                // r is always valid since we only move r when target is not found
                if (nums[m] <= target) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
        }
        if (flag == 0) {
            // since l is always valid for finding the
            // first target element, we just check l
            return (l < nums.length && nums[l] == target) ? l : -1;
        }
        else {
            // since r is always valid for finding the
            // last target element, we just check r
            return (r >= 0 && nums[r] == target) ? r : -1;
        }
    }
}
