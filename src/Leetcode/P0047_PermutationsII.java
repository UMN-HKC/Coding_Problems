package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0047_PermutationsII {

    // initial approach: backtracking
    // The basic idea is to traverse each element in the array and dfs all permutations for
    // each element, and backtrack afterwards. When list reaches num size, we add it to
    // our result list.
    // The difference between PermutationI and this question is that to avoid duplicate
    // permutations, we use boolean array to mark visited indices, compared with PermutationI
    // where we used set to mark visited numbers. Since, we do not care about duplicate
    // numbers in a permutation, a set is not appropriate in here.

    // time: O(n!)
    // T(n) = n * T(n - 1) = O(n!)

    // space: O(n) stack space

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        // use boolean array instead of set is because we want duplicate numbers in
        // our result but not duplicate permutations
        dfs(res, list, nums, new boolean[nums.length]);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] visited) {
        if (nums.length == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            list.add(nums[i]);
            visited[i] = true;
            dfs(res, list, nums, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
        return;
    }
}
