package Leetcode;

public class P0238_ProductOfArrayExceptSelf {

    // approach 1: left to right, right to left scans
    // the basic idea is that when scanning form left to right, we accumulate a product
    // of all left elements and set it to the element of current index, in this way,
    // we store the product of each element's left product. Then, we scan from right to left
    // with the same idea. After two scans, all elements' value is the product except self
    // Note that for the first element, we check if it is 0 or not first.

    // time: O(n)
    // space: O(1) except return array

    public int[] productExceptSelf(int[] nums) {
        int left = 1;
        int right = 1;
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && nums[i] == 0) {
                res[i] = 0;
            }
            else {
                res[i] = left;
            }
            left *= nums[i];
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
