package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P0051_NQueens {


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        // driver function
        dfs(res, board, 0);
        return res;
    }

    public void dfs(List<List<String>> res, char[][] board, int r) {

        if (r == board.length) {
            res.add(constructResult(board));
            return;
        }
        for (int i = 0; i < board.length; i++) {
            if (isValid(board, r, i)) {
                board[r][i] = 'Q';
                dfs(res, board, r + 1);
                board[r][i] = '.';
            }
        }
    }
    public boolean isValid(char[][] board, int row, int col) {
        // check all cols
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        //check 45 degree
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        //check 135
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
    public List<String> constructResult(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
    public boolean isWithin(char[][] board, int x, int y) {
        int len = board.length;
        return x >= 0 && y >= 0 && x < len && y < len;
    }
}
