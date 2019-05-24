package Leetcode;

public class P0080_RemoveDuplicateSortedII {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int i = 2;
        int j = 2;
        while (j < nums.length) {
            if (nums[j] != nums[i - 2]) {
                nums[i++] = nums[j];
            }
            j++;
        }
        return i;
    }
}
