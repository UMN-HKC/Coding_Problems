package Leetcode;

import java.util.*;

public class P0200_NumOfIslands {
    int[][] dirs = {{1,0},{0,1}, {-1,0}, {0,-1}};

    // approach 1: dfs

    public int numIslands_dfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int cnt = 0;
        int h = grid.length;
        int w = grid[0].length;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    dfs(grid, i, j);
                }
            }
        }
        return cnt;
    }
    public void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isInside(grid.length, grid[0].length, x, y)) {
                if (grid[x][y] == '1') {
                    dfs(grid, x, y);
                }
            }
        }
        return;
    }

    // approach 2: bfs

    public int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int cnt = 0;
        int h = grid.length;
        int w = grid[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (grid[i][j] == '1') {
                    cnt++;
                    bfs(grid, i, j);
                }
            }
        }
        return cnt;
    }
    public void bfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] dir: dirs) {
                int x = pos[0] + dir[0];
                int y = pos[1] + dir[1];
                if (isInside(grid.length, grid[0].length, x, y) && grid[x][y] == '1') {
                    grid[x][y] = '0';
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return;
    }

    public boolean isInside(int h, int w, int x, int y) {
        return (x < h) && (x >= 0) && (y < w) && (y >= 0);
    }

    // approach 3: union find

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] ids = new int[m * n];
        Arrays.fill(ids, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int curId = i * n + j;
                    ids[curId] = curId;
                    if (j > 0 && grid[i][j - 1] == '1') {
                        int leftId = i * n + j - 1;
                        int px = find(ids, curId);
                        int py = find(ids, leftId);
                        ids[px] = ids[py];
                    }
                    if (i > 0 && grid[i - 1][j] == '1') {
                        int topId = (i - 1) * n + j;
                        int px = find(ids, curId);
                        int py = find(ids, topId);
                        ids[px] = ids[py];
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int id : ids) {
            if (id != -1) set.add(find(ids, id));
        }
        return set.size();
    }
    private int find(int[] ids, int x) {
        if (ids[x] != x) {
            ids[x] = find(ids, ids[x]);
        }
        return ids[x];
    }
}
