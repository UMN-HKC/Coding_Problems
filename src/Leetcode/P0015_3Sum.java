package Leetcode;
import java.util.*;

public class P0015_3Sum {


    // initial approach: two pointers

    // time: O(n^2)

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // since array is sorted and sort does not worse time complexity
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int start = i+1;
            int end = nums.length - 1;
            int target = -nums[i];

            while (start < end) {
                if (nums[start] + nums[end] == target) {
                    List<Integer> list = new ArrayList<>();
                    res.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                    end--;
                    // skip duplicate numbers since they are already added
                    while (start < end && nums[start] == nums[start - 1]) start++;
                    while (start < end && nums[end] == nums[end + 1]) end--;
                }
                else if (nums[start] + nums[end] < target) {
                    start++;
                }
                else {
                    end--;
                }
            }
        }
        return res;
    }
}
