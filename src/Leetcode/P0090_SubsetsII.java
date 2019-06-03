package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0090_SubsetsII {

    // Initallly stuck at the condition to skip the index
    // idea borrowed from discussion board: (illustrated below)

    // time: O(2^n)
    // space: O(n)

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, list, 0, nums);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int index, int[] nums) {
        res.add(new ArrayList<>(list));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            // when i > index, it means we have explored all possible subsets with start of
            // index. Thus, if i > index and nums[i] == nums[i - 1], it means we have encountered
            // a duplicate element as a starting element for the new subset, so we skip it.
            if (i > index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            dfs(res, list, i+1, nums);
            list.remove(list.size() - 1);
        }
        return;
    }
}
