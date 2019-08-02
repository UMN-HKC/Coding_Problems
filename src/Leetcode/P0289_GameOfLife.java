package Leetcode;

public class P0289_GameOfLife {

    // approach 1:
    // reproduce = 2(previously dead)
    // die = 3 (previously alive)
    // 1.overpopulation (more than 3 live)
    // 2.under-population (less than 2 live)

    int[][] dirs = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};

    public void gameOfLife(int[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int deadNear = 0, liveNear = 0;
                for (int[] dir : dirs) {
                    int y = i + dir[0];
                    int x = j + dir[1];
                    if (x < 0 || y < 0 || y >= m || x >= n) {
                        deadNear++;
                    }
                    else {
                        if (board[y][x] == 0 || board[y][x] == 2) {
                            deadNear++;
                        }
                        else if (board[y][x] == 1 || board[y][x] == 3) {
                            liveNear++;
                        }
                    }
                }
                if (board[i][j] == 0) {
                    if (liveNear == 3) {
                        board[i][j] = 2;
                    }
                }
                else {
                    if (liveNear < 2 || liveNear > 3) {
                        board[i][j] = 3;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] == 3 ? 0 : (board[i][j] == 2 ? 1 : board[i][j]);
            }
        }
        return;
    }
}
