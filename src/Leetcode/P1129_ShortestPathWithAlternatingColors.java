package Leetcode;
import java.util.*;

public class P1129_ShortestPathWithAlternatingColors {


    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        // couldn't figure out initially, idea borrowed from discussion board

        // queue: stores the node and its incoming edge color
        // reds and blues: stores nodes and their children(outgoing edges)
        // visited: stores visited nodes(first character is visited node's number and second
            // character indicates edge color). In this way, we won't skip a node just because
            // it is visited since it might have a different incoming edge color.

        List<Integer>[] reds = new ArrayList[n], blues = new ArrayList[n];
        Queue<int[]> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        q.offer(new int[]{0, 0});

        // build graph with both red and blue edges
        for (int[] red : red_edges) {
            int from = red[0], to = red[1];
            if (reds[from] == null) {
                reds[from] = new ArrayList<>();
            }
            reds[from].add(to);
        }
        for (int[] blue : blue_edges) {
            int from = blue[0], to = blue[1];
            if (blues[from] == null) {
                blues[from] = new ArrayList<>();
            }
            blues[from].add(to);
        }
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                String s = cur[0] + "" + cur[1];
                if (!visited.add(s)) continue;
                // only set it once to ensure shortest path
                if (res[cur[0]] == -1) {
                    res[cur[0]] = step;
                }
                // check if this node has red outgoing edges
                if (reds[cur[0]] != null) {
                    if (cur[1] == 0 || cur[1] == 2) {
                        for (int child : reds[cur[0]]) {
                            q.offer(new int[]{child, 1});
                        }
                    }
                }
                // check if this node has blue outgoing edges
                if (blues[cur[0]] != null) {
                    if (cur[1] == 0 || cur[1] == 1) {
                        for (int child : blues[cur[0]]) {
                            q.offer(new int[]{child, 2});
                        }
                    }
                }

            }
            step++;
        }
        return res;
    }
}
