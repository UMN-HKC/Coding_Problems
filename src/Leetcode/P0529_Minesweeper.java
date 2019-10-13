package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0529_Minesweeper {

    // approach 1: DFS (DFS is cleaner and not prone to errors for this problem IMO)
    // The basic idea is that if the click is on a bomb, we are done right away.
    // Otherwise, it is an empty unrevealed cell.
    // The next thing we do is to calculate number of bombs adjacent to this cell.
    //  - If number of bombs is greater than 0, we modify the cell's value to the
    //    number of bombs and we are done.
    //  - If number of bombs is equal to 0, we mark cell as 'B' and then explore
    //    its adjacent cells and recurse on 'E' cells.

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c = click[1];
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        int cnt = getNumberOfBombs(board, r, c);
        if (cnt > 0) {
            board[r][c] = (char)('0' + cnt);
            return board;
        }
        board[r][c] = 'B';
        for (int[] dir : dirs) {
            int y = r + dir[0];
            int x = c + dir[1];
            if (y >= 0 && x >= 0 && y < board.length && x < board[0].length && board[y][x] == 'E') {
                updateBoard(board, new int[]{y, x});
            }
        }
        return board;

    }

    // approach 2: BFS

    // The key for BFS with this problem is that for each polled empty cell from
    // queue, we first calculate the number of bombs adjacent to this cell.
    // Next, if the cell has 1 or more than 1 bombs adjacent to it, we modify the cell
    // value and not offer it to the queue. Otherwise, we explore its neighbors

    public char[][] updateBoard_2(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        else {
            Queue<int[]> q = new LinkedList<>();
            board[click[0]][click[1]] = 'B';
            q.offer(new int[]{click[0], click[1]});
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int cnt = getNumberOfBombs(board, cur[0], cur[1]);
                // only when no bomb nearby, we explore its neighbors
                if (cnt == 0) {
                    for (int[] dir : dirs) {
                        int r = cur[0] + dir[0];
                        int c = cur[1] + dir[1];
                        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] != 'E')
                            continue;
                        board[r][c] = 'B';
                        q.offer(new int[]{r, c});
                    }
                }
                else {
                    board[cur[0]][cur[1]] = (char)('0' + cnt);
                }
            }
            return board;
        }
    }

    //------------------------------------------------------------------------------------------

    public static int[][] dirs = {{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    public int getNumberOfBombs(char[][] board, int r, int c) {
        int cnt = 0;
        for (int[] dir : dirs) {
            int y = r + dir[0];
            int x = c + dir[1];
            if (y >= 0 && x >= 0 && y < board.length && x < board[0].length && board[y][x] == 'M') {
                cnt++;
            }
        }
        return cnt;
    }




}
