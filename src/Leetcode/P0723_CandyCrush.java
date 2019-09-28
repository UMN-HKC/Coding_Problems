package Leetcode;

import java.util.ArrayList;
import java.util.List;

public class P0723_CandyCrush {

    // approach 1:
    // couldn't figure out initially

    // The basic idea is that for each cell, we check its four directions: if either its vertical or
    // horizontal have same value of length more than 2, we add this cell's position to the list
    // After traversing the whole board, we check if the list is empty or not. If it is empty, it means
    // we have crushed all candies. Otherwise, we mark all positions in the list to 0. And then for
    // each column, we drop all non-zero elements.

    // note that we check up, down, left and right, we only need to explore at most distance 2 away
    // from the original cell, because 3 same values already mean we need to crush this candy.

    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        while (true) {
            List<int[]> removeList = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) continue;
                    int up = i, down = i, left = j, right = j;
                    while (up >= 0 && i - up < 3 && board[up][j] == board[i][j]) up--;
                    while (down < m && down - i < 3 && board[down][j] == board[i][j]) down++;
                    while (left >= 0 && j - left < 3 && board[i][left] == board[i][j]) left--;
                    while (right < n && right - j < 3 && board[i][right] == board[i][j]) right++;
                    if (right - left > 3 || down - up > 3) removeList.add(new int[]{i, j});
                }
            }
            if (removeList.size() == 0) break;
            else {
                cleanAndAdjust(board, removeList);
            }
        }
        return board;
    }
    public void cleanAndAdjust(int[][] board, List<int[]> removeList) {
        for (int[] pos : removeList) {
            board[pos[0]][pos[1]] = 0;
        }
        int m = board.length, n = board[0].length;
        for (int col = 0; col < n; col++) {
            int t = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][col] != 0) {
                    int temp = board[i][col];
                    board[i][col] = board[t][col];
                    board[t][col] = temp;
                    t--;
                }
            }
        }
    }
}
