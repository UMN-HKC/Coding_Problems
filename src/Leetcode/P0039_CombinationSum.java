package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0039_CombinationSum {

    // initial approach: dfs

    // time: O(2^n * n)
    // here we multiply a n because each time we find an answer, we copy our result once
    // T(n) = T(n - 1) + T(n - 2) + ... + T(1) = O(2^n)
    // it is like a binary stream, each time we choose either 1 or 0

    // space: O(n) = stack space

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return res;
        }
        dfs(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] candidates, int index, int target) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(res, list, candidates, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }
        return;
    }

    // same approach without for loop

    public List<List<Integer>> combinationSum_2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // Arrays.sort(candidates);
        helper(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int idx, int target) {
        if (target == 0) {
            res.add(new ArrayList(list));
            return;
        }
        if (target < 0 || idx >= nums.length) return;
        helper(res, list, nums, idx + 1, target);
        list.add(nums[idx]);
        // note that in here we still pass in idx instead of idx + 1,
        // since we want same number as well
        helper(res, list, nums, idx, target - nums[idx]);
        list.remove(list.size() - 1);
    }
}
