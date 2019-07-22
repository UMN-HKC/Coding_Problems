package Leetcode;

public class P0075_SortColors {

    // approach 1:
    // the basic idea is to move all 0's to left and all 2's to right

    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int zero = -1, two = nums.length, itr = 0;
        while (itr < two) {
            if (zero < itr && nums[itr] == 0) {
                zero++;
                swap(nums, zero, itr);
            }
            else if (nums[itr] == 2) {
                two--;
                swap(nums, itr, two);
            }
            else {
                itr++;
            }
        }
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
