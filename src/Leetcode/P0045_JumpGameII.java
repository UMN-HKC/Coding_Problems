package Leetcode;

import java.util.Arrays;

public class P0045_JumpGameII {

    // (TLE) initial approach: brute force, still greedy
    // basically, for each position starting at index 1, we traverse its left index
    // and update steps[i] if nums[j] >= i - j

    public int jump(int[] nums) {
        int[] steps = new int[nums.length];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // steps[i] != steps[j] is essential, otherwise when steps[i] == steps[j], steps[i] will be
                // incremented making it even greater which is not right.
                // alternatively, we can change Math.min to a if condition.

                if (nums[j] >= i - j && steps[i] != steps[j]) {
                    steps[i] = Math.min(steps[j], steps[i]) + 1;
                }
            }
        }
        return steps[steps.length - 1];
    }


    public int jump_improved(int[] nums) {
        int[] steps = new int[nums.length];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j) {
                    steps[i] = Math.min(steps[j], steps[i]) + 1;
                    // since steps array will always be in increasing order
                    // once updated, no need to go further and this
                    // also deals with the condition problem we have in last previous version
                    break;
                }
            }
        }
        return steps[steps.length - 1];
    }

    // approach 2: still greedy thinking, but smartly applies implicit breadth first search thinking
    // we can view curEnd as the current level, and curFarthest as the next level for bfs.

    // or could think at each position, we can a laddar of length nums[i].
    // we keep a current laddar and maxladder
    // ref: https://www.youtube.com/watch?v=vBdo7wtwlXs

    // time: O(n)

    public int jump_greedy_linear(int[] A) {
        int jumps = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < A.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + A[i]);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
        }
        return jumps;
    }

}
