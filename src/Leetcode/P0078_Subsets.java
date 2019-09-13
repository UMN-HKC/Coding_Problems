package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0078_Subsets {

    // initial approach: dfs

    // time: O(2^n)
    // space O(n)

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, 0, nums);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int index, int[] nums) {
        res.add(new ArrayList<>(list));
        if (index >= nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(res, list, i + 1, nums);
            list.remove(list.size() - 1);
        }
        return;
    }

    //approach 2:
    // The idea is that for each position, we either choose it or not
    // There are only 2 branches from this stack for our next level
    // recursion call.

    // time: O(2^n)
    // space: O(n)

    public List<List<Integer>> subsets_2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[] nums, int idx) {
        if (idx >= nums.length) {
            res.add(new ArrayList(list));
            return;
        }
        dfs(res, list, nums, idx + 1);
        list.add(nums[idx]);
        dfs(res, list, nums, idx + 1);
        list.remove(list.size() - 1);
    }
}
