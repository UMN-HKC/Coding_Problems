package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0934_ShortestBridge {

    // approach 1: dfs + bfs

    public static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int shortestBridge(int[][] A) {
        int r = A.length, c = A[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[r][c];
        boolean found = false;
        for (int i = 0; i < r && !found; i++) {
            for (int j = 0; j < c && !found; j++) {
                if (A[i][j] == 1) {
                    dfs(A, q, visited, i, j);
                    found = true;
                }
            }
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int y = cur[0] + dir[0];
                    int x = cur[1] + dir[1];
                    if (y >= 0 && x >= 0 && y < r && x < c && !visited[y][x]) {
                        if (A[y][x] == 1) {
                            return cnt;
                        }
                        visited[y][x] = true;
                        q.offer(new int[]{y, x});
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
    private void dfs(int[][] A, Queue<int[]> q, boolean[][] visited, int i, int j) {
        visited[i][j] = true;
        q.offer(new int[]{i, j});
        int r = visited.length, c = visited[0].length;
        for (int[] dir : dirs) {
            int y = i + dir[0];
            int x = j + dir[1];
            if (y >= 0 && x >= 0 && y < r && x < c && !visited[y][x] && A[y][x] == 1) {
                dfs(A, q, visited, y, x);
            }
        }
    }
}
