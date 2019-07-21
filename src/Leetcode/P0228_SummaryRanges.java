package Leetcode;
import java.util.*;

public class P0228_SummaryRanges {

    // approach 1: initial approach
    // the basic idea is to iterate through the array and record contiguous range
    // with start and end indicating its head and tail. Update result list when we
    // encounter a non-contiguous range.

    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        if (nums.length == 1) {
            res.add(nums[0] + "");
            return res;
        }
        int start = nums[0], end = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != end + 1) {
                if (start == end) {
                    res.add(new String(start+""));
                }
                else {
                    res.add(new String(start + "->" + end));
                }
                start = nums[i];
                end = nums[i];
            }
            else {
                end++;
            }
            if (i == nums.length - 1) {
                if (start == end) {
                    res.add(new String(start+""));
                }
                else {
                    res.add(new String(start + "->" + end));
                }
            }
        }
        return res;
    }

    // simplify previous code
    public List<String> summaryRanges_simplify(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        if (nums.length == 1) {
            res.add(nums[0] + "");
            return res;
        }
        int i = 0;
        while (i < nums.length) {
            int start = nums[i];
            while (i + 1 < nums.length && nums[i+1] == nums[i] + 1) i++;
            if (start == nums[i]) {
                res.add(nums[i] + "");
            }
            else {
                res.add(start + "->" + nums[i]);
            }
            i++;
        }
        return res;
    }
}
