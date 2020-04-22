package Leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class P0785_IsGraphBipartite {

    // approach 1: bfs, coloring

    // The basic idea is to traverse the whole graph and each time
    // color the node with a color and color its neighbors with
    // a different color. Once we find a node's neighbor is already
    // colored with the same color, we know the graph is not bipartite.

    // time: O(V + E)
    // space: O(V)


    public boolean isBipartite(int[][] graph) {
        int m = graph.length;
        int[] status = new int[m];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            if (status[i] == 0) {
                q.offer(i);
                status[i] = 1;
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int neighbor : graph[cur]) {
                        if (status[neighbor] == 0) {
                            q.offer(neighbor);
                            status[neighbor] = status[cur] == 1 ? 2 : 1;
                        }
                        else if (status[neighbor] == status[cur]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
