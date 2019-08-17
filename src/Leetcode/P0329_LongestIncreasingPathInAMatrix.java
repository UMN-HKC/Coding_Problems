package Leetcode;

public class P0329_LongestIncreasingPathInAMatrix {

    // approach 1: DFS (TLE)
    // time: O(2^(m+n))
    int max = 0;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        boolean[][]  visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(matrix, visited, 1, i, j);
            }
        }
        return max;
    }
    public void dfs(int[][] matrix, boolean[][] visited, int len, int x, int y) {
        if (len > max) {
            max = len;
        }
        visited[x][y] = true;
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newY >= 0 && newX < matrix.length && newY < matrix[0].length &&
                    !visited[newX][newY] && matrix[x][y] < matrix[newX][newY]) {
                dfs(matrix, visited, len + 1, newX, newY);
            }
        }
        visited[x][y] = false;
    }

    // approach 2: DFS with memo
    // time: O(m*n)
    // space: O(m*n)

    int max = 0;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath_dfs_with_memo(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(dfs(memo, matrix, i, j), max);
            }
        }
        return max;
    }
    public int dfs(int[][] memo, int[][] matrix, int x, int y) {
        if (memo[x][y] != 0) {
            return memo[x][y];
        }
        int curLen = 1;
        for (int[] dir : dirs) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newY >= 0 && newX < matrix.length && newY < matrix[0].length
                    && matrix[x][y] < matrix[newX][newY]) {
                int len = dfs(memo, matrix, newX, newY);
                curLen = Math.max(curLen, 1 + len);
            }
        }
        memo[x][y] = curLen;
        return curLen;
    }

    // other approaches: graph / bfs
}
