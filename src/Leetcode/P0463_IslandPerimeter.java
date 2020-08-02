package Leetcode;

public class P0463_IslandPerimeter {

    // approach 1: dfs

    // time: O(m * n)
    // space: O(m * n)
    
    int res = 0;
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int islandPerimeter(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean found = false;
        for (int i = 0; i < m && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, visited);
                    found = true;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length) {
                if (grid[r][c] == 0) {
                    res++;
                }
                else if (!visited[r][c]) {
                    dfs(grid, r, c, visited);
                }
            }
            else {
                res++;
            }
        }
    }

    // approach 2

    // time: O(m * n)
    // space: O(1)

    public int islandPerimeter_2(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int result = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    result += 4;

                    if (r > 0 && grid[r-1][c] == 1) {
                        result -= 2;
                    }

                    if (c > 0 && grid[r][c-1] == 1) {
                        result -= 2;
                    }
                }
            }
        }

        return result;
    }
}
