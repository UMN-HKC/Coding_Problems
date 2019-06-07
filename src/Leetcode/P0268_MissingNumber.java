package Leetcode;

public class P0268_MissingNumber {

    // initial approach: similar idea with "first missing number"

    // time: O(n)
    // space: O(1)

    public int missingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            while (i < nums.length && nums[i] != i && nums[i] != nums.length) {
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
            i++;
        }
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        return nums.length;
    }

    // approach 2: since every number XOR itself is 0
    // so we XOR all numbers and their indices, the missing number will remain

    public int missingNumber_bit(int[] nums) {
        // set num = nums.length to compensate for index of nums.length
        int num = nums.length;
        for (int i = 0; i < nums.length; i++) {
            num ^= i;
            num ^= nums[i];
        }
        return num;
    }
}
