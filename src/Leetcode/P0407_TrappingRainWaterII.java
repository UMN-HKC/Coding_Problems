package Leetcode;

import java.util.PriorityQueue;

public class P0407_TrappingRainWaterII {

    // approach 1: BFS + priority queue
    // link1: https://github.com/awangdev/LintCode/blob/master/Java/Trapping%20Rain%20Water%20II.java
    // link2: https://www.cnblogs.com/grandyang/p/5928987.html

    // First, we need to realize that the maximum water that the map can hold is determined
    // by the outer area, so we bfs starting from the outermost area. Note that the priority
    // queue places lower height cell on top of it. So each time we pop a cell, it is the
    // lowest height that we can get. Then when we explore its neighbor cells, we have ensured
    // that the volumn of water we add to our result won't leak, because we compare the neighbor
    // with the lowest height from the outer area.

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) return 0;
        int vol = 0;
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{0, i, heightMap[0][i]});
            pq.offer(new int[]{m - 1, i, heightMap[m - 1][i]});
            visited[0][i] = true;
            visited[m - 1][i] = true;
        }
        for (int i = 1; i < m - 1; i++) {
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            for (int[] dir : dirs) {
                int r = cur[0] + dir[0];
                int c = cur[1] + dir[1];
                if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) continue;
                if (heightMap[r][c] < cur[2]) {
                    vol += cur[2] - heightMap[r][c];
                }
                visited[r][c] = true;
                pq.offer(new int[]{r, c, Math.max(cur[2], heightMap[r][c])});
            }
        }
        return vol;
    }
}
