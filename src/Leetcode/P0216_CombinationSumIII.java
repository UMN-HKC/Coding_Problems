package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0216_CombinationSumIII {

    // initial approach: dfs

    // time: O((9!/(k! * (9 - k)!)) * k)
    // space: O(k)

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList();
        dfs(res, list, 1, k, n);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int num, int k, int target) {
        if (target < 0) return;
        if (list.size() == k) {
            if (target == 0) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        for (int i = num; i <= 9; i++) {
            list.add(i);
            dfs(res, list, i+1, k, target - i);
            list.remove(list.size() - 1);
        }
        return;
    }
}
