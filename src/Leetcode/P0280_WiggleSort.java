package Leetcode;
import java.util.*;

public class P0280_WiggleSort {

    // approach 1: sort first

    // time: O(nlogn)
    // space: O(1)

    public void wiggleSort_1(int[] nums) {
        Arrays.sort(nums);
        if (nums.length <= 2) return;
        for (int i = 1; i < nums.length - 1; i+=2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i+1] = temp;
        }
        return;
    }

    // approach 2: linear approach

    // time: O(n)
    // space: O(1)

    // Still kind of brute force, but the basic idea is to set a flag to indicate
    // whether the next trend should be increasing or decreasing. If the next trend
    // contradicts with what next two elements' value actually is, swap the two items,
    // and reverse the trend and proceed to next two elements.

    public void wiggleSort_2(int[] nums) {
        if (nums == null || nums.length < 2) return;
        boolean isIncre = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (isIncre) {
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            else {
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }
            isIncre = !isIncre;
        }
    }
    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
