package Leetcode;

import java.util.HashSet;
import java.util.Set;

public class P0694_NumberOfDistinctIslands {

    // approach 1: dfs
    // The basic idea is to pass an additional (0,0) point as starting point to record
    // the relative position of our shape string which we will be building, and the rest
    // is the basic dfs.

    int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public int numDistinctIslands(int[][] grid) {
        Set<String> visited = new HashSet<>();
        int r = grid.length, c = grid[0].length;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    String cur = new String(dfs(grid, i, j, 0, 0));
                    visited.add(cur);
                }
            }
        }

        return visited.size();
    }
    public StringBuilder dfs(int[][] grid, int i, int j, int y, int x) {
        grid[i][j] = 0;
        StringBuilder sb = new StringBuilder(y + ":" + x).append("->");
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) continue;
            sb.append(dfs(grid, r, c, y + dir[0], x + dir[1]));
        }
        return sb;
    }
}
