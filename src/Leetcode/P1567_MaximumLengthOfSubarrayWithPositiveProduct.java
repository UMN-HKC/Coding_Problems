package Leetcode;

public class P1567_MaximumLengthOfSubarrayWithPositiveProduct {

     // approach 1
    // The idea is to keep track of index of most recent start point(index of 0)
    // and the leftmost index of a negative number, and number of negative numbers
    // when we meet a 0, we need to reset start point to this index, and reset
    // other variables as well
    
     public int getMaxLen(int[] nums) {
         int n = nums.length;
         int res = 0;
         int negCnt = 0;
         int startIdx = -1;
         int leftmostNegIdx = -1;
         for (int i = 0; i < nums.length; i++) {
             if (nums[i] == 0) {
                 startIdx = i;
                 leftmostNegIdx = -1;
                 negCnt = 0;
             }
             else if (nums[i] < 0) {
                 if (leftmostNegIdx == -1) {
                     leftmostNegIdx = i;
                 }
                 negCnt++;
             }
             if (negCnt % 2 == 0) {
                 res = Math.max(res, i - startIdx);
             }
             else {
                 res = Math.max(res, i - leftmostNegIdx);
             }
         }
         return res;
     }
}
