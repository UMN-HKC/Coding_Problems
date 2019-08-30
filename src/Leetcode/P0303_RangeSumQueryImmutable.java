package Leetcode;

public class P0303_RangeSumQueryImmutable {

    // approach 1: prefix sum

    int[] runningSum;
    public NumArray(int[] nums) {
        runningSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            runningSum[i + 1] = runningSum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return runningSum[j + 1] - runningSum[i];
    }
}
