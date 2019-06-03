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
}
