package Leetcode;

import java.util.*;

public class P0743_NetworkDelayTime {

    // approach 1: dijkstra's algorithm (bfs)
    // finding single source all path shortest path
    // find max length among all shortest paths

    // time: O(VlogV + E)
    // space: O(V + E)

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int t = time[2];
            if (!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new int[]{to, t});
        }
        int maxTime = 0;
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{K, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int t = cur[1];
            if (visited[from]) continue;
            visited[from] = true;
            N--;
            maxTime = Math.max(maxTime, t);
            if (map.containsKey(from)) {
                List<int[]> neighbors = map.get(from);
                for (int[] neighbor : neighbors) {
                    int[] next = new int[]{neighbor[0], t + neighbor[1]};
                    pq.offer(next);
                }
            }
        }

        return N == 0 ? maxTime : -1;
    }
}
