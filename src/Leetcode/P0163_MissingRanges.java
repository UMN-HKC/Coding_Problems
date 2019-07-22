package Leetcode;
import java.util.*;

public class P0163_MissingRanges {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            add(res, (long)lower - 1, (long)upper + 1);
            return res;
        }
        int i = 0;
        // convert and initialize variables to long type to handle overflow
        long llower = (long)lower, lupper = (long)upper;
        // end is the end number of contiguous range
        // cur is the current number we are visiting
        // initially set end = lower bound - 1 to handle the first number
        long end = llower - 1, cur = nums[0];

        while (i < nums.length) {
            // skip duplicates and contiguous range
            while (i == 0 || (i < nums.length && nums[i] == nums[i-1] + 1) || (i < nums.length && nums[i] == nums[i-1])) {
                if (i == 0 && cur > end + 1) {
                    add(res, end, cur);
                }
                i++;
                end = cur;
                cur = i < nums.length ? nums[i] : lupper+1;
            }
            // add missing range
            if (i < nums.length && cur > end + 1) {
                add(res, end, cur);
                i++;
                end = cur;
                cur = i < nums.length ? nums[i] : lupper+1;
            }
        }
        // final check upper bound
        if (lupper > nums[nums.length - 1]) {
            add(res, nums[nums.length - 1], lupper+1);
        }
        return res;
    }
    public void add(List<String> res, long l, long u) {
        if (u - l == 2) {
            res.add((l+1) + "");
        }
        else {
            res.add((l+1) + "->" + (u-1));
        }
    }
}
