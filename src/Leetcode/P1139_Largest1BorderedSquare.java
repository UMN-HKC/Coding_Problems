package Leetcode;

public class P1139_Largest1BorderedSquare {

    // approach 1: Brute force (initial approach) could be optimized later

    // the basic idea is that for each point, we see it as upper left corner
    // of a square and we will try to expand it until we reach the limit of
    // our grid.

    public int largest1BorderedSquare(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int max = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int side = 1; side <= Math.min(m, n); side++) {
            for (int i = 0; i <= m - side; i++) {
                for (int j = 0; j <= n - side; j++) {
                    if (max < side) {
                        if (isValid(grid, i, j, side)) {
                            System.out.println(side);
                            max = side;
                        }
                    }
                }
            }
        }
        return max * max;
    }
    public boolean isValid(int[][] grid, int x, int y, int side) {
        for (int i = x; i < x + side; i++) {
            if (grid[i][y] != 1) return false;
            if (grid[i][y + side - 1] != 1) return false;
        }
        for (int i = y; i < y + side; i++) {
            if (grid[x][i] != 1) return false;
            if (grid[x + side - 1][i] != 1) return false;
        }
        return true;
    }
}
