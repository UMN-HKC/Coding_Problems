package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0286_WallsAndGates {
    int[][] dirs = {{1,0},{0,1}, {-1,0}, {0,-1}};
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        int h = rooms.length;
        int w = rooms[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
        return;
    }
    public void bfs(int[][] rooms, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            for (int[] dir: dirs) {
                int x = pos[0] + dir[0];
                int y = pos[1] + dir[1];
                if (isInside(rooms.length, rooms[0].length, x, y) && rooms[x][y] != -1
                        && rooms[x][y] != 0 && rooms[x][y] > rooms[pos[0]][pos[1]] + 1) {
                    rooms[x][y] = rooms[pos[0]][pos[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return;
    }

    public boolean isInside(int h, int w, int x, int y) {
        return (x < h) && (x >= 0) && (y < w) && (y >= 0);
    }
}
