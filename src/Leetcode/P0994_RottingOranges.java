package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0994_RottingOranges {

    // approach 1: bfs

    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        int numOfFresh = 0;
        int m = grid.length;
        int n = grid[0].length;
        int timeElapsed = 0;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    numOfFresh++;
                }
                else if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        // if there's no fresh oranges at the beginning
        if (numOfFresh == 0) {
            return 0;
        }
        while (!q.isEmpty()) {
            int size = q.size();
            timeElapsed++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int r = cur[0] + dir[0];
                    int c = cur[1] + dir[1];
                    if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                        numOfFresh--;
                        grid[r][c] = 2;
                        q.offer(new int[]{r, c});
                        if (numOfFresh == 0) {
                            return timeElapsed;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
