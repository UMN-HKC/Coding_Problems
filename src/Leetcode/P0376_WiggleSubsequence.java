package Leetcode;

public class P0376_WiggleSubsequence {

    // couldn't figure out initially, idea borrowed from discussion board
    // approach 1: greedy
    // The basic idea is to realize that this question is essentially same as
    // asking how many min and max peaks the array has.
    // When you have increasing or decreasing sub-sequence longer than 2 just ignore
    // all middle elements and use the first and the last only (you don't gain anything
    // from the middle ones). Also ignore duplicates.

    public int wiggleMaxLength_greedy(int[] nums) {
        if (nums == null || nums.length < 2) return nums.length;
        int res = 1;
        int state = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1] && (state == 0 ||state == -1)) {
                state = 1;
                res++;
            }
            else if (nums[i] < nums[i - 1] && (state == 0 || state == 1)) {
                state = -1;
                res++;
            }
        }
        return res;
    }

    // approach 2: DP
    // The basic idea is that at each one position, the number has 3 states:
    // 1. increasing
    // 2. decreasing
    // 3. stay same
    // When it is increasing(nums[i] > nums[i - 1]), the max wiggle sequence length
    // comes from its previous decreasing sequence
    // When it is decreasing(nums[i] < nums[i - 1]), the max wiggle sequence length
    // comes from its previous increasing sequence
    // When it is same(nums[i] == nums[i - 1]), the max wiggle sequence length stays same
    // Therefore, we will use two arrays (up and down) to denote increasing and decreasing states

    public int wiggleMaxLength_dp(int[] nums) {
        if (nums == null || nums.length < 2) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = down[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(down[i - 1] + 1, up[i - 1]);
            }
            else if (nums[i] < nums[i - 1]) {
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            }
            else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[nums.length - 1], down[nums.length - 1]);
    }

    // DP with space optimized to O(1)
    public int wiggleMaxLength_dp2(int[] nums) {
        if (nums == null || nums.length < 2) return nums.length;
        int up = 1, down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(down + 1, up);
            }
            else if (nums[i] < nums[i - 1]) {
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);
    }
}
