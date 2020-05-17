package Leetcode;

import java.util.*;

public class P1443_MinimumTimeToCollectAllApplesInATree {

    // approach 1: dfs

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        buildGraph(map, edges);
        Set<Integer> visited = new HashSet<>();
        return 2 * dfs(map, 0, hasApple, visited);
    }
    private int dfs(Map<Integer, List<Integer>> map, int node, List<Boolean> hasApple, Set<Integer> visited) {
        visited.add(node);
        int res = 0;
        for (int neighbor : map.get(node)) {
            if (!visited.contains(neighbor)) {
                res += dfs(map, neighbor, hasApple, visited);
            }
        }
        if (res == 0 && !hasApple.get(node)) {
            return 0;
        }

        return node == 0 ? res : 1 + res;
    }
    private void buildGraph(Map<Integer, List<Integer>> map, int[][] edges) {
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            map.putIfAbsent(from, new ArrayList<>());
            map.putIfAbsent(to, new ArrayList<>());
            map.get(from).add(to);
            map.get(to).add(from);
        }
    }
}
