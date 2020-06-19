package Leetcode;

import java.util.Arrays;

public class P1464_MaximumProductOfTwoElementsInAnArray {

    // approach 1: O(n^2)

    public int maxProduct(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] - 1) * (nums[j] - 1) > max) {
                    max = (nums[i] - 1) * (nums[j] - 1);
                }
            }
        }
        return max;
    }

    // approach 2: O(nlogn)

    public int maxProduct_2(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    // approach 3: O(n)

    public int maxProduct_3(int[] nums) {
        int max = 0, secondMax = 0;
        for (int num : nums) {
            if (num > max) {
                secondMax = max;
                max = num;
            }
            else if (num > secondMax) {
                secondMax = num;
            }
        }
        return (max - 1) * (secondMax - 1);
    }
}
