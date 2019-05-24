package Leetcode;

public class P0189_RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
        return;
    }
}
