package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0216_CombinationSumIII {

    // initial approach: dfs

    // time: O((9!/(k! * (9 - k)!)) * k)   (select k numbers, slightly different from I and II)
    // space: O(k)  stack space

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

    // approach 2:
    // choose or not choose

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 1, n, k);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int num, int rest, int k) {
        if (k == 0 && rest == 0) {
            res.add(new ArrayList(list));
            return;
        }
        // early termination
        if (num > 9 || rest - num < 0) return;
        dfs(res, list, num + 1, rest, k);
        list.add(num);
        dfs(res, list, num + 1, rest - num, k - 1);
        list.remove(list.size() - 1);
    }
}
