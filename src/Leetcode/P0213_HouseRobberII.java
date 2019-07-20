package Leetcode;

public class P0213_HouseRobberII {

    // approach 1: initial approach
    // slightly complicated than second approach, pass a boolean value to indicate whether we
    // will rob the first house or not and code in the helper function will twist a little
    // bit based on whether robbing first or not.

    public int rob_1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return Math.max(robHelper(nums, false), robHelper(nums, true));
    }
    public int robHelper(int[] nums, boolean robFirst) {
        int max = 0;
        // initialize two variables based on robFirst
        int robPre = robFirst ? nums[0] : 0;
        int notRobPre = 0;
        for (int i = 1; i < nums.length; i++) {
            // if first house is not robbed, then robPre should rob the second house
            // as its starting point
            if (i == 1 && !robFirst) {
                robPre = nums[i];
            }
            // if first house was robbed, the last house is skipped
            else if (i == nums.length - 1 && robFirst) {
                continue;
            }
            else {
                int temp = robPre;
                robPre = Math.max(robPre, notRobPre + nums[i]);
                notRobPre = temp;
            }
        }
        return Math.max(robPre, notRobPre);
    }

    // approach 2:
    // based on House Robber I, in this problem, we will simply define a rob range and pass that
    // to the helper function(either rob the first house and not rob the last house, or rob the
    // last house and not rob the first house)

    public int rob_2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robHelper(nums, 0, nums.length - 2), robHelper(nums, 1, nums.length - 1));
    }
    public int robHelper(int[] nums, int s, int e) {
        int max = 0;
        int rob = nums[s], notRob = 0;
        for (int i = s + 1; i <= e; i++) {
            int temp = rob;
            rob = Math.max(rob, notRob + nums[i]);
            notRob = temp;
        }
        return Math.max(rob, notRob);
    }
}
