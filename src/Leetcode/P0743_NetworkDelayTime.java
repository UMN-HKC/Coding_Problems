package Leetcode;

import java.util.*;

public class P0743_NetworkDelayTime {

    // approach 1: dijkstra's algorithm (bfs)
    // finding single source all path shortest path
    // find max length among all shortest paths

    // time: O(VlogV + E)
    // space: O(V + E)

    public int networkDelayTime(int[][] times, int N, int K) {
        boolean[] visited = new boolean[N + 1];
        Map<Integer, List<int[]>> map = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int[] time : times) {
            int s = time[0];
            int d = time[1];
            int t = time[2];
            map.putIfAbsent(s, new ArrayList<>());
            map.get(s).add(new int[]{d, t});
        }
        pq.offer(new int[]{K, 0});
        int endTime = 0;
        int cnt = N;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int curNode = pair[0];
            int curTime = pair[1];
            endTime = curTime;
            // here we check if the node is already visited because we might have already
            // visited and updated this node but note that we might have pushed multiple
            // same node with different time to our queue. So once we have done with one
            // node, even if there are more records on this node, we want to skip them.
            if (visited[curNode]) continue;
            visited[curNode] = true;
            cnt--;
            if (cnt == 0) break;
            if (map.containsKey(curNode)) {
                for (int[] neighbor : map.get(curNode)) {
                    if (!visited[neighbor[0]]) {
                        int[] next = new int[]{neighbor[0], neighbor[1] + curTime};
                        pq.offer(next);
                    }
                }
            }
        }
        return cnt == 0 ? endTime : -1;
    }
}
