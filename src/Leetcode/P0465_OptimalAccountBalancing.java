package Leetcode;

import java.util.HashMap;
import java.util.Map;

public class P0465_OptimalAccountBalancing {

    // approach 1: dfs + backtracking
    // The basic idea is to build the debt array and then try to solve each positive debt with
    // the following negative debt, which means we will explore all possible ways and update the
    // result when a smaller number of transactions found. Note that we use cnt to prune when
    // the current number of transactions is already greater than the global min.

    // time: O(n!)

    int min = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        int[] debt = buildDebt(transactions);
        dfs(debt, 0, 0);
        return min;
    }
    public void dfs(int[] debt, int idx, int cnt) {
        if (cnt >= min) return;
        while (idx < debt.length && debt[idx] == 0) idx++;
        if (idx == debt.length) {
            min = Math.min(min, cnt);
            return;
        }
        for (int i = idx + 1; i < debt.length; i++) {
            if (debt[i] * debt[idx] < 0) {
                debt[i] += debt[idx];
                dfs(debt, idx + 1, cnt + 1);
                debt[i] -= debt[idx];
            }
        }

    }
    public int[] buildDebt(int[][] t) {
        int len = t.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] op : t) {
            int from = op[0];
            int to = op[1];
            int amount = op[2];
            map.put(from, map.getOrDefault(from, 0) + amount);
            map.put(to, map.getOrDefault(to, 0) - amount);
        }
        int[] debt = new int[map.size()];
        int i = 0;
        for (int val : map.values()) {
            debt[i++] = val;
        }
        return debt;
    }
}
