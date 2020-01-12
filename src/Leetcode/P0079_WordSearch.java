package Leetcode;

public class P0079_WordSearch {

    // approach 1: dfs
    // time: O(m * n * (4 ^ L)) where M*N is the size of the board and we have 4^L for each cell because of the recursion
    // space: O(m * n + L)

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j] && dfs(board, i, j, 0, word, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, int r, int c, int idx, String word, boolean[][] visited) {
        if (idx == word.length() - 1) {
            return true;
        }
        boolean found = false;
        int m = board.length, n = board[0].length;
        visited[r][c] = true;
        for (int[] dir : dirs) {
            int y = r + dir[0];
            int x = c + dir[1];
            if (y < m && x < n && y >= 0 && x >= 0 && !visited[y][x] && board[y][x] == word.charAt(idx + 1)) {
                if (dfs(board, y, x, idx+1, word, visited)) {
                    found = true;
                    break;
                }
            }
        }
        visited[r][c] = false;
        return found;
    }
}
