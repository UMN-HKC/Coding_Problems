package Leetcode;

public class P0260_SingleNumberIII {

    // idea borrowed from discussion board
    // Basically, utilizing the fact that two distinct numbers will have distinct bits at some
    // bit position.
    // First pass, we get the XOR of these two distinct numbers(all the other duplicates XOR
    // themselves and become 0). Second pass, we divide all numbers into two groups using XOR
    // as well. Duplicate numbers will become 0 eventually and rest is the two distinct numbers
    // we want.

    // time: O(n)
    // space: O(1)

    public int[] singleNumber(int[] nums) {
        int[] res = {0,0};
        int dif = 0;
        for (int num : nums) {
            dif ^= num;
        }
        // operation to find the rightmost set bit
        dif &= ~(dif-1);

        for (int num : nums) {
            if ((dif & num) == dif) {
                res[0] ^= num;
            }
            else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
