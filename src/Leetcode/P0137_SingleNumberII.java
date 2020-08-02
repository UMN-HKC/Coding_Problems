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

    // approach 2: easier to understand and explain
    // https://www.youtube.com/watch?v=puXcQpwgcD0
    // https://www.youtube.com/watch?v=ZbTXZ2_YAgI

    // still O(n) solution

    public int singleNumber_2(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1;
            }
            sum %= 3;
            res = res | (sum << i);
        }
        return res;
    }

}
