package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0317_ShortestDistanceFromAllBuildings {

    // approach 1: BFS
    // The basic idea is to apply BFS and start searching from buildings. For each building, we will do
    // bfs level by level. We will have distance[][], reach[][] array.
    // distance[i][j]: sum of distance from cell[i][j] to all building cells in the grid
    // reach[i][j]: num of buildings that can be reached from cell[i][j]


    int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n];
        int[][] reach = new int[m][n];
        int numOfBuildings = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numOfBuildings++;
                    boolean[][] visited = new boolean[m][n];
                    bfs(grid, distance, reach, visited, i, j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && reach[i][j] == numOfBuildings && distance[i][j] < min) {
                    min = distance[i][j];
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    public void bfs(int[][] grid, int[][] distance, int[][] reach, boolean[][] visited, int i, int j) {
        int level = 1;
        int m = distance.length, n = distance[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i, j});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int r = cur[0] + dir[0];
                    int c = cur[1] + dir[1];
                    if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c] || grid[r][c] == 2 || grid[r][c] == 1) {
                        continue;
                    }
                    distance[r][c] += level;
                    visited[r][c] = true;
                    reach[r][c]++;
                    q.offer(new int[]{r, c});
                }
            }
            level++;
        }
    }
}
