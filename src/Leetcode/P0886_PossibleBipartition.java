package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P0886_PossibleBipartition {

    // approach 1: dfs
    // The basic idea is to construct the relationship as a graph and then apply either
    // bfs or dfs to traverse the graph and 'color' each node with a color out of
    // two 'colors' that is different from the 'color' just used in the last call stack
    // once we can not color the node with required color, return false

    public boolean possibleBipartition(int N, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] dislike : dislikes) {
            map.putIfAbsent(dislike[0], new ArrayList<>());
            map.putIfAbsent(dislike[1], new ArrayList<>());
            map.get(dislike[0]).add(dislike[1]);
            map.get(dislike[1]).add(dislike[0]);
        }
        int[] colors = new int[N];
        for (int i = 1; i <= N; i++) {
            if (colors[i - 1] == 0) {
                if (!color(map, colors, i, -1)) {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean color(Map<Integer, List<Integer>> map, int[] colors, int cur, int color) {
        colors[cur - 1] = color;

        if (map.containsKey(cur)) {
            for (int neighbor : map.get(cur)) {
                if (colors[neighbor - 1] == colors[cur - 1]) {
                    return false;
                }
                if (colors[neighbor - 1] != 0) {
                    continue;
                }
                if (!color(map, colors, neighbor, -color)) {
                    return false;
                }
            }
        }
        return true;
    }
}
