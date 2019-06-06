package Leetcode;

public class P0154_FindMinimumInRotatedSortedArrayII {

    // the idea is borrowed from HuaHua
    // https://www.youtube.com/watch?v=aCb1zKMimDQ

    // time: O(n) for worst case (2 2 2 2 2 3 1 2)
    // space: O(1)

//    [incre2][max][min][incre1]

    public int findMin_iterative(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[end]) {
                end = mid;
            }
            else if (nums[mid] > nums[end]) {
                start = mid + 1;
            }
            else {
                end--;
            }
        }
        return nums[start];
    }


    // approach 2: divide and conquer
    // Basically,
    // - when there are only 1-2 elements, simply return the lesser one
    // - otherwise,
    //      - we check if the array is sorted by comparing the first and last element
    //      - if not sorted, we make recursive calls on two parts


    public int findMin_recursive(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int l, int r) {
        if (l == r) return nums[l];
        if (l + 1 == r) return Math.min(nums[l], nums[r]);
        if (nums[l] < nums[r]) {
            return nums[l];
        }
        else {
            int mid = l + (r - l) / 2;
            return Math.min(helper(nums, l, mid), helper(nums, mid + 1, r));
        }
    }
}
