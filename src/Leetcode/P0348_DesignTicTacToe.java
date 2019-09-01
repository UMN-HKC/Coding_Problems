package Leetcode;

public class P0348_DesignTicTacToe {

    // approach 1: brute force

    // used a 2d board array to keep placement status and for each move
    // we check horizontal, vertical. diagonal and antidiagonal.

    // time: O(n)

    char[][] board;
    public TicTacToe(int n) {
        board = new char[n][n];
    }

    public int move(int row, int col, int player) {
        if (player == 1) {
            board[row][col] = 'X';
            if (check(row, col, 'X')) {
                return 1;
            }
        }
        else {
            board[row][col] = 'O';
            if (check(row, col, 'O')) {
                return 2;
            }
        }
        return 0;
    }
    public boolean check(int row, int col, char c) {
        int n = board.length;
        boolean found = true;
        // horizontal & vertical check
        for (int i = 0; i < n; i++) {
            if (board[row][i] != c) {
                found = false;
            }
        }
        if (found) return true;
        found = true;
        for (int i = 0; i < n; i++) {
            if (board[i][col] != c) {
                found = false;
            }
        }
        if (found) return true;
        found = true;
        // diagonal check for 45 degree
        int cnt = 1;
        int i = 1;
        while ((row - i >= 0 && col + i < n) || (row + i < n && col - i >= 0)) {
            if (row - i >= 0 && col + i < n) {
                if (board[row - i][col + i] == c) {
                    cnt++;
                }
            }
            if (row + i < n && col - i >= 0) {
                if (board[row + i][col - i] == c) {
                    cnt++;
                }
            }
            i++;
        }
        if (cnt >= n) return true;
        cnt = 1;
        i = 1;
        while ((row - i >= 0 && col - i >= 0) || (row + i < n && col + i < n)) {
            if (row - i >= 0 && col - i >= 0) {
                if (board[row - i][col - i] == c) {
                    cnt++;
                }
            }
            if (row + i < n && col + i < n) {
                if (board[row + i][col + i] == c) {
                    cnt++;
                }
            }
            i++;
        }
        return cnt >= n;
    }

    // approach 2:
    // The key is to realize that we only need a row array, a column array
    // to keep track of placement status for each row and column. This is
    // doable when we mark player1's movement as 1 and player2's movement as -1.

    // And we only need two variables to keep track of main diagonal and main anti diagonal
    // because it is only possible for two main diagonals to have n places.

    // time: O(1)

    int[] rowCnt;
    int[] colCnt;
    int diagCnt;
    int antiDiagCnt;
    public TicTacToe(int n) {
        rowCnt = new int[n];
        colCnt = new int[n];
        diagCnt = 0;
        antiDiagCnt = 0;
    }

    public int move(int row, int col, int player) {
        int size = rowCnt.length;
        int toAdd = player == 1 ? 1 : -1;
        rowCnt[row] += toAdd;
        colCnt[col] += toAdd;
        if (row - col == 0) {
            diagCnt += toAdd;
        }
        if (row + col == size - 1) {
            antiDiagCnt += toAdd;
        }
        if (rowCnt[row] == size || colCnt[col] == size
                || diagCnt == size || antiDiagCnt == size) return 1;
        if (-rowCnt[row] == size || -colCnt[col] == size
                || -diagCnt == size || -antiDiagCnt == size) return 2;
        return 0;
    }
}
