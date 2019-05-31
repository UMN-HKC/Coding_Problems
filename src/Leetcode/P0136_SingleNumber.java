package Leetcode;

public class P0136_SingleNumber {

    public int singleNumber(int[] nums) {
        int dif = 0;
        for (int num : nums) {
            dif ^= num;
        }
        return dif;
    }
}
