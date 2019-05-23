package Leetcode;

public class P0130_SurroundedRegion {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int h = board.length;
        int w = board[0].length;
        // dfs the first and last row
        for (int i = 0; i < w; i++) {
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if (board[h-1][i] == 'O') {
                dfs(board, h-1, i);
            }
        }
        // dfs the first and last column
        for (int i = 0; i < h; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][w-1] == 'O') {
                dfs(board, i, w-1);
            }
        }
        // flipping all other 'O's to 'X', and '*'s to 'O'
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                // need to flip 'O's first, otherwise will go into loop
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
        return;
    }
    public void dfs(char[][] board, int i, int j) {
        board[i][j] = '*';
        if (isInside(board.length, board[0].length, i + 1, j) && board[i+1][j] == 'O') {
            dfs(board, i+1, j);
        }
        if (isInside(board.length, board[0].length, i, j + 1) && board[i][j+1] == 'O') {
            dfs(board, i, j+1);
        }
        if (isInside(board.length, board[0].length, i - 1, j) && board[i-1][j] == 'O') {
            dfs(board, i-1, j);
        }
        if (isInside(board.length, board[0].length, i, j - 1) && board[i][j-1] == 'O') {
            dfs(board, i, j-1);
        }
        return;
    }
    public boolean isInside(int h, int w, int x, int y) {
        return (x < h) && (x >= 0) && (y < w) && (y >= 0);
    }
}
