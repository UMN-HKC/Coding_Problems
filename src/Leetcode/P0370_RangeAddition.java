package Leetcode;

public class P0370_RangeAddition {

    // approach 1: O(n)

    // no need to iterate through range for each update. first iteration, we add
    // the update to the start time, and subtract the update on (end + 1) time.
    // For the second iteration, we accumulate the previous updates and apply to
    // the current position.

    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] update : updates) {
            int s = update[0];
            int e = update[1];
            res[s] += update[2];
            if (e + 1 < length) {
                res[e + 1] -= update[2];
            }
        }
        for (int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
