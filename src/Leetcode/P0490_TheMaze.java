package Leetcode;

public class P0490_TheMaze {

    // approach 1: dfs
    // The idea is to apply dfs is to dfs only on the stop point. So within each dfs call,
    // we will use while loop to find the stop point for each direction. And then recurse
    // on that point
    
    public boolean hasPath(int[][] grid, int[] start, int[] end) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        return dfs(grid, m, n, start, end, visited);
    }
    private boolean dfs(int[][] grid, int m, int n,
                               int[] start, int[] end, boolean[][] visited) {
        // stop point reaches end point
        int y = start[0];
        int x = start[1];
        if (y == end[0] && x == end[1]) {
            return true;
        }
        if (visited[y][x]) {
            return false;
        }
        visited[y][x] = true;

        // to the left
        int xToTheLeft = x;
        while (xToTheLeft - 1 >= 0 && grid[y][xToTheLeft - 1] == 0) {
            xToTheLeft--;
        }
        if (xToTheLeft != x) {
            if (dfs(grid, m, n, new int[]{y, xToTheLeft},end, visited)) {
                return true;
            }
        }

        // to the right
        int xToTheRight = x;
        while (xToTheRight + 1 < n && grid[y][xToTheRight + 1] == 0) {
            xToTheRight++;
        }
        if (xToTheRight != x) {
            if (dfs(grid, m, n, new int[]{y, xToTheRight},end, visited)) {
                return true;
            }
        }

        // to the top
        int yToTheTop = y;
        while (yToTheTop - 1 >= 0 && grid[yToTheTop - 1][x] == 0) {
            yToTheTop--;
        }
        if (yToTheTop != y) {
            if (dfs(grid, m, n, new int[]{yToTheTop, x}, end,visited)) {
                return true;
            }
        }

        // to the bottom
        int yToTheBottom = y;
        while (yToTheBottom + 1 < m && grid[yToTheBottom + 1][x] == 0) {
            yToTheBottom++;
        }
        if (yToTheBottom != y) {
            if (dfs(grid, m, n, new int[]{yToTheBottom, x}, end,visited)) {
                return true;
            }
        }
        return false;
    }
}
