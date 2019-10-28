package Leetcode;
import java.util.*;

public class P0163_MissingRanges {

    // approach 1:
    // the problem could be divided to 3 parts:
    // - deal with missing range before lower
    // - deal with missing range within lower and upper
    // - deal with missing range after the last number but before upper
    // remember to take care of empty array first

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        // when nums is empty
        if (nums == null || nums.length == 0) {
            res.add(lower == upper ? String.valueOf(lower) : lower + "->" + upper);
            return res;
        }
        // deal with the potential start missing
        if (lower < nums[0]) {
            res.add(lower < nums[0] - 1 ? (lower + "->" + (nums[0] - 1)) : String.valueOf(lower));
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) continue;
            if (nums[i] != nums[i + 1] - 1) {
                res.add(nums[i] == nums[i + 1] - 2 ? String.valueOf(nums[i] + 1) : (nums[i] + 1) + "->" + (nums[i + 1] - 1));
            }
        }
        // potential end missing
        if (upper > nums[nums.length - 1]) {
            res.add(upper > nums[nums.length - 1] + 1 ? (nums[nums.length - 1] + 1) + "->" + upper : String.valueOf(upper));
        }
        return res;
    }
}
