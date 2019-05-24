package Leetcode;

public class P0027_RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int rmv = 0;
        int itr = 0;
        int tail = nums.length - 1;
        while (itr <= tail) {
            if (nums[itr] == val) {
                swap(nums, itr, tail);
                tail--;
                rmv++;
                continue;
            }
            itr++;
        }
        return nums.length - rmv;
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[r];
        nums[r] = nums[l];
        nums[l] = temp;
        return;
    }
}
