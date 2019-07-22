package Leetcode;

public class P0283_MoveZeroes {

    // approach 1:
    // find all non-zero numbers and move to front, set all remaining slots
    // after non-zero elements to 0.

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int nonZero = -1, itr = 0;
        while (itr < nums.length) {
            if (nums[itr] != 0) {
                nums[++nonZero] = nums[itr];
            }
            itr++;
        }
        while (++nonZero < nums.length) {
            nums[nonZero] = 0;
        }
        return;
    }
}
