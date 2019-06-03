package Leetcode;

public class P0055_JumpGame {

    // initial approach:
    public boolean canJump_initial(int[] nums) {
        if (nums == null || nums.length <= 1) return true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (i != 0)
                nums[i] = Math.max(nums[i], nums[i - 1] - 1);
            if (nums[i] <= 0) return false;
        }
        return nums[nums.length - 2] != 0 ? true : false;
    }
}
