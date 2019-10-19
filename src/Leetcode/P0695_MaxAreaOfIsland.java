package Leetcode;

public class P0695_MaxAreaOfIsland {

    // approach 1: dfs
    // The basic idea is that we iterate through the grid and once we encounter 1, we depth
    // first search on the cell and get the max area extending from this cell. Update the
    // final result. Note that we need to change 1 to other value to indicate a visited cell.

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int maxArea = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(dfs(grid, i, j), maxArea);
                }
            }
        }
        return maxArea;
    }
    public int dfs(int[][] grid, int i, int j) {
        grid[i][j] = -1;
        int area = 1;
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 1) {
                continue;
            }
            area += dfs(grid, r, c);
        }
        return area;
    }
}
