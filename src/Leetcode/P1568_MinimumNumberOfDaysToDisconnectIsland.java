package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P1568_MinimumNumberOfDaysToDisconnectIsland {

    // approach 1: initial idea (dfs+backtracking) TLE
    // When you aren't smart enough to realize the fact answer is at most 2 days. You come up with exponential time complexity solution :(

    int res = Integer.MAX_VALUE;
    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cells.add(new int[]{i, j});
                }
            }
        }
        if (cells.size() == 0 || isDisconnected(grid)) {
            return 0;
        }
        dfs(grid, cells, 0, 0);
        return res;
    }
    private void dfs(int[][] grid, List<int[]> cells, int idx, int cnt) {
        if (res <= cnt) {
            return;
        }
        if (isDisconnected(grid)) {
            res = Math.min(res, cnt);
            return;
        }
        if (idx == cells.size()) {
            return;
        }

        dfs(grid, cells, idx + 1, cnt);
        int[] cord = cells.get(idx);
        grid[cord[0]][cord[1]] = 0;
        dfs(grid, cells, idx + 1, cnt + 1);
        grid[cord[0]][cord[1]] = 1;

    }
    private boolean isDisconnected(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    if (cnt == 1) {
                        return true;
                    }
                    traverse(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        return cnt == 0;
    }
    private void traverse(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        traverse(grid, visited, i + 1, j);
        traverse(grid, visited, i - 1, j);
        traverse(grid, visited, i, j + 1);
        traverse(grid, visited, i, j - 1);
    }

    // approach 2: when you realize the answer is at most 2
    // the idea is to realize the answer is at most 2 days.
    // for example to disconnect the following island, at most you need 2 days
    // 1 1 1 1              1 1 1 1
    // 1 1 1 1      -->     1 1 1 1
    // 1 1 1 1      -->     1 1 1 0
    // 1 1 1 1              1 1 0 1

    public int minDays(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // check if it's initially disconnected
        if (isDisconnected(grid)) {
            return 0;
        }
        // check for 1 day
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (isDisconnected(grid)) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        // if not 1 day, then it's 2 days
        return 2;
    }
    private boolean isDisconnected(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    if (cnt == 1) {
                        return true;
                    }
                    traverse(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        return cnt == 0;
    }
    private void traverse(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        traverse(grid, visited, i + 1, j);
        traverse(grid, visited, i - 1, j);
        traverse(grid, visited, i, j + 1);
        traverse(grid, visited, i, j - 1);
    }
}
