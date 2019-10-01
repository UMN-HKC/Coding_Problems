package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0797_AllPathsFromSourceToTarget {

    // approach 1: dfs
    // since it is a DAG, just dfs and no need to use boolean[] visited

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        int n = graph.length;
        dfs(res, new ArrayList<>(), graph, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> list, int[][] graph, int idx) {
        list.add(idx);
        if (idx == graph.length - 1) {
            res.add(new ArrayList(list));
        }
        else {
            for (int edge : graph[idx]) {
                dfs(res, list, graph, edge);
            }
        }
        list.remove(list.size() - 1);
    }

}

