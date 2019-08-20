package Leetcode;

public class P0037_SudokuSolver {

    // approach 1: backtracking
    // the basic idea is that for each '.', we will try to solve it by exploring all
    // values from 1-9 until one is valid which means it does not break the sudoku rule
    // if all fail to satisfy the rule, it means the previous place is wrong, so we
    // need to backtrack.

    // time: exponential O(9^n)

    // driver method
    public void solveSudoku(char[][] board) {
        dfs(board, 0, 0);
    }
    public boolean dfs(char[][] board, int r, int c) {
        if (c == 9) {
            c = 0;
            r++;
        }
        if (r == board.length) {
            return true;
        }
        if (board[r][c] != '.') return dfs(board, r, c+1);
        if (board[r][c] == '.') {
            for (char num = '1'; num <= '9'; num++) {
                if (isValid(board, r, c, num)) {
                    board[r][c] = num;
                    if (dfs(board, r, c + 1)) {
                        return true;
                    }
                    board[r][c] = '.';
                }
            }
        }
        return false;
    }
    public boolean isValid(char[][] board, int r, int c, char num) {
        int row = 3 * (r / 3);
        int col = 3 * (c / 3);
        for (int i = 0; i < 9; i++) {
            if (board[r][i] != '.' && board[r][i] == num) return false;
            if (board[i][c] != '.' && board[i][c] == num) return false;
            if (board[row + i / 3][col + i % 3] != '.' && board[row + i / 3][col + i % 3] == num) return false;
        }
        return true;
    }
}
