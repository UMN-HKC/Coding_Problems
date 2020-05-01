package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0077_Combinations {

    // initial approach: dfs

    // time: O(2^k * k)
    // here we multiply a k because each time we find an answer, we copy our result once
    // space: O(k) = stack space

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(res, list, 1, n, k);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int num, int n, int k) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = num; i <= n; i++) {
            list.add(i);
            dfs(res,list, i + 1, n, k-1);
            list.remove(list.size() - 1);
        }
        return;
    }

    // approach 2: choose or not choose
    // time: O(2^k * k)
    // space: O(k)

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 1, n, k);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int idx, int n, int k) {
        if (k == 0) {
            res.add(new ArrayList(list));
            return;
        }
        if (idx > n) return;
        dfs(res, list, idx + 1, n, k);
        list.add(idx);
        dfs(res, list, idx + 1, n, k - 1);
        list.remove(list.size() - 1);
    }
}
