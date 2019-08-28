package Leetcode;

import java.util.Arrays;

public class P0259_3SumSmaller {


    // approach 1: two pointers

    // The key is to realize that positions of elements does not matter
    // i < j < k is eye-catching. Since we only need to find three different
    // indices where their elements' sum is less than the target, we can sort
    // the array first.

    // time: O(n^2)

    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int total = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    total += right - left;
                    left++;
                }
                else {
                    right--;
                }
            }
        }
        return total;
    }
}
