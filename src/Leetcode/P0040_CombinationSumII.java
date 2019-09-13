package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0040_CombinationSumII {

    // initial approach: dfs
    // was stuck at how to avoid duplicates. The key idea to realize that within the for loop in our
    // dfs, i > index means that we have just returned from the last stack so this is the point that we
    // need to check for duplicates.

    // time: O(2^n)
    // T(n) = T(n - 1) + T(n - 2) + ... + T(1) = O(2^n)
    // it is like a binary stream, each time we choose either 1 or 0

    // space: O(n) = stack space

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(res, list, candidates, 0, 0, target);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] candidates, int index, int sum, int target) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i - 1] == candidates[i])   continue;
            list.add(candidates[i]);
            dfs(res, list, candidates, i + 1, sum + candidates[i], target);
            list.remove(list.size() - 1);
        }
        return;
    }

    // approach 2:
    // The key in this approach is to recurse on the choice of choose or not choose to
    // add the current number and use a prevAdded flag to handle duplicate cases

    public List<List<Integer>> combinationSum2_2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(res, new ArrayList<>(), candidates, 0, target, false);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int idx, int rest, boolean prevAdded) {
        if (rest == 0) {
            res.add(new ArrayList(list));
            return;
        }
        if (idx >= nums.length) return;
        if (rest - nums[idx] < 0) return;
        dfs(res, list, nums, idx + 1, rest, false);
        if (idx != 0 && nums[idx] == nums[idx - 1] && !prevAdded) return;
        list.add(nums[idx]);
        dfs(res, list, nums, idx + 1, rest - nums[idx], true);
        list.remove(list.size() - 1);
    }
}
