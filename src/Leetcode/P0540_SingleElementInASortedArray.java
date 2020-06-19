package Leetcode;

public class P0540_SingleElementInASortedArray {

    // approach 1: binary search

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] != nums[m + 1]) {
                if ((n - m - 1) % 2 == 0) {
                    r = m;
                }
                else {
                    l = m + 1;
                }
            }
            else {
                if (m % 2 == 0) {
                    l = m + 1;
                }
                else {
                    r = m;
                }
            }
        }
        return nums[l];
    }
}
