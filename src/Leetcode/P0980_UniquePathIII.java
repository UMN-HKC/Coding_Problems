package Leetcode;

public class P0980_UniquePathIII {

    // initial approach: DFS brute force

    // time: O(4^(R*C))  (R: # of rows; C: # of columns)
    // space: O(R * C) stack space

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int uniquePathsIII(int[][] grid) {
        int width = grid.length;
        int length = grid[0].length;
        // find out number of empty cells we need to cover
        int[] numOfEmptyCells = getNumOfEmptyCells(grid);
        int[] res = new int[1];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, numOfEmptyCells, res);
                }
            }
        }
        return res[0];
    }
    public void dfs(int[][] grid, int x, int y, int[] rest, int[] res) {
        if (grid[x][y] == -1) return;
        if (grid[x][y] == 2) {
            if (rest[0] == 0) {
                res[0]++;
            }
            return;
        }
        // do not decrement number of empty cells if the current one is start point
        if (grid[x][y] != 1) {
            rest[0]--;
        }
        // store the current one so that we could check later
        int temp = grid[x][y];
        grid[x][y] = -1;
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (isValidMove(grid.length, grid[0].length, newX, newY)) {
                dfs(grid, newX, newY, rest, res);
            }
        }
        // if the current one is not start then it must be empty, backtrack it
        if (temp != 1) {
            grid[x][y] = 0;
            rest[0]++;
        }
        return;
    }
    public int[] getNumOfEmptyCells(int[][] grid) {
        int[] sum = new int[1];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum[0] += grid[i][j] == 0 ? 1 : 0;
            }
        }
        return sum;
    }
    public boolean isValidMove(int width, int length, int x, int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < length);
    }
}
