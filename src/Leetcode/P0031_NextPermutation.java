package Leetcode;

import java.util.Arrays;

public class P0031_NextPermutation {

    // approach 1:
    // link: https://www.youtube.com/watch?time_continue=484&v=quAS1iydq7U

    // Basically, the key is to understand how permutation is generated. In this problem, we need to realize
    // that the strictly decreasing section is on its last permutation, so we need to look at element right
    // before this section.
    // step1: find the index right before the strictly decreasing section
    // step2: if: not found, simply reverse the array since its strictly decreasing,
    //        else: find the smallest element that is greater than the element right before the decreasing section
    // step3: swap them and sort the previously decreasing section
    // eg: [6,2,1,5,4,3,0]

    // time: O(nlogn)
    // space: O(1)

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int n = nums.length;
        int firstDecreasingIdx = n - 2;
        while (firstDecreasingIdx >= 0 && nums[firstDecreasingIdx] >= nums[firstDecreasingIdx + 1]) {
            firstDecreasingIdx--;
        }
        if (firstDecreasingIdx == -1) {
            Arrays.sort(nums);
            return;
        }
        for (int i = n - 1; i > firstDecreasingIdx; i--) {
            if (nums[i] > nums[firstDecreasingIdx]) {
                int temp = nums[i];
                nums[i] = nums[firstDecreasingIdx];
                nums[firstDecreasingIdx] = temp;
                Arrays.sort(nums, firstDecreasingIdx + 1, n);
                break;
            }
        }
    }

    // optimize space
    // simply reversing the subarray is enough

    // time: O(n)
    // space: O(1)

    public void nextPermutation_2(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int k = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                k = i;
                break;
            }
        }
        if (k == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        for (int i = nums.length - 1; i > k; i--) {
            if (nums[i] > nums[k]) {
                swap(nums, i, k);
                reverse(nums, k + 1, nums.length - 1);
                return;
            }
        }
        return;
    }
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return;
    }
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
        return;
    }
}
