package Leetcode;

public class P0026_RemoveDuplicateSorted {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = 0;
        int r = 1;
        while (r < nums.length) {
            if (nums[l] != nums[r]) {
                nums[l+1] = nums[r];
                l++;
                r++;
            }
            else {
                r++;
            }
        }
        return l+1;
    }
}
