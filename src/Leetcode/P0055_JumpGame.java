package Leetcode;

public class P0055_JumpGame {

    // approach 1: DP
    // dp[i]: can reach position i

    // time: O(n^2)
    // space: O(n)

    public boolean canJump(int[] nums) {
        if (nums[0] == 0 && nums.length > 1) return false;
        int len = nums.length;
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len - 1];
    }

    // approach 2: greedy
    // The basic idea is to keep a dis variable to keep track of maximum distance
    // we can reach at current point. Each time after we make a jump to a new position,
    // we update dis. Once dis becomes greater or equal to end position, we return true

    // time: O(n)
    // space: O(1)

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
