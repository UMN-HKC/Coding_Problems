package Leetcode;

public class P0137_SingleNumberII {

    // genius idea borrowed from discussion board
    // Basically, we need to apply boolean algebra thinking for this solution.
    // So, except the number we are looking for, each number appears 3 times, we need
    // to record their state with 2 bits(00: 0 time; 10: 1 time; 01: 2 times; 00: 3 times)
    // thus, using bit manipulation, after iterating the whole array, the number left in
    // 'ones' is the number that appears 1 time.

    // time: O(n)
    // space: O(1)

    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & (~twos);
            twos = (twos ^ num) & (~ones);
        }
        return ones;
    }
}
