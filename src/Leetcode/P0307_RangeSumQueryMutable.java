package Leetcode;

public class P0307_RangeSumQueryMutable {

    // hard, couldn't figure out initially except brute force. But learned a new data structure
    // approach 1: Fenwick tree(binary indexed tree)
    // great explanation and illustration with pics from Huahua: https://www.youtube.com/watch?v=WbafSgetDDk
    // different than DP, it also did precalculation, but every node stores partial sum

    public FenwickTree ft;
    public int[] nums;
    public NumArray(int[] nums) {
        ft = new FenwickTree(nums.length);
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            ft.update(i+1, nums[i]);
        }
    }

    public void update(int i, int val) {
        ft.update(i+1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return ft.sumRange(j+1) - ft.sumRange(i);
    }
    // Fenwick tree class
    class FenwickTree {
        int[] nums;
        public FenwickTree(int n) {
            nums = new int[n + 1];
        }
        public int sumRange(int index) {
            int sum = 0;
            while (index > 0) {
                sum += nums[index];
                index -= index & (-index);
            }
            return sum;
        }
        public void update(int index, int delta) {
            while (index < nums.length) {
                nums[index] += delta;
                index += index & (-index);
            }
            return;
        }
    }
}
