package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0039_CombinationSum {

    // initial approach: dfs

    // time: O(C(n,k)) = O((n!/(k! * (n - k)!)) * k)
    // here we multiply a k because each time we find an answer, we copy our result once
    // space: O(k) = stack space

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
}
