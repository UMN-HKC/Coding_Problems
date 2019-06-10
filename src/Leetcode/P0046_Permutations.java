package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P0046_Permutations {

    // initial approach: backtracking
    // The basic idea is to traverse each element in the array and dfs all permutations for
    // each element, and backtrack afterwards. When list reaches num size, we add it to
    // our result list. We use set to distinguish which elements have already been visited.
    // Since there's no duplicate element(as compared to PermutationII with duplicates), it
    // is same to use set (as compared to PermutationII where we use boolean array instead)

    // time: O(n!)
    // T(n) = n * T(n - 1) = O(n!)

    // space: O(n) stack space

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        List<Integer> list = new ArrayList<>();
        dfs(res, list, nums, new HashSet<>());
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, Set<Integer> set) {
        if (nums.length == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            set.add(nums[i]);
            dfs(res, list, nums, set);
            list.remove(list.size() - 1);
            set.remove(nums[i]);
        }
        return;
    }
}
