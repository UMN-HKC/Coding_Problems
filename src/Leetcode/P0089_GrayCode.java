package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0089_GrayCode {

    // couldn't figure out initially
    // idea borrowed from leetcode discussion board
    // basically, the n bits result could be generated by (n-1) bits result
    // we could simply '|' an extra MSB of (n - 1) bits result

    // time: O(2^n) since we are generating 2^n numbers and each operation is of O(1)
    // space: O(2^n)

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int size = res.size();
            // be careful that we must iterate the previous result from back to front
            // so that we only flip 1 bit which satisfies the requirement
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) | (1<<i));
            }
        }
        return res;
    }
}
