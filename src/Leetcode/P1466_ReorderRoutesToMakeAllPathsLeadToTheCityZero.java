package Leetcode;

import java.util.*;

public class P1466_ReorderRoutesToMakeAllPathsLeadToTheCityZero {

    // approach 1: dfs
    // https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/discuss/662137/Java-Straight-Forward-DFS-(Generic-Solution)-with-Detail-Explanation


    public int minReorder(int n, int[][] connections) {
        boolean[] visited = new boolean[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] connection : connections) {
            map.putIfAbsent(connection[0], new ArrayList<>());
            map.get(connection[0]).add(connection[1]);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cnt += dfs(map, visited, i);
            }
        }
        return cnt;
    }
    private int dfs(Map<Integer, List<Integer>> map, boolean[] visited, int cur) {
        visited[cur] = true;
        int cnt = 0;
        if (map.containsKey(cur)) {
            for (int neighbor : map.get(cur)) {
                if (!visited[neighbor]) {
                    cnt += 1 + dfs(map, visited, neighbor);
                }
            }
        }

        return cnt;
    }
}
